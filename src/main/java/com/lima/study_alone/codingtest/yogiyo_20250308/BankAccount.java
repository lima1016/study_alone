package com.lima.study_alone.codingtest.yogiyo_20250308;

enum StateInfo {
  LOGGED_IN, LOGGED_OUT, SUSPENDED, ERROR
}

interface BankAccountState {
  StateInfo login(String password);
  StateInfo logout();
  StateInfo unlock(int resetCode);
  StateInfo withdrawMoney(int amount);
}

class BankAccount {
  private final BankAccountState loggedIn;
  private final BankAccountState loggedOut;
  private final BankAccountState suspended;
  private BankAccountState bankAccountState;
  private int cashBalance;
  private final String password;
  private int passwordRetries;
  private final int resetCode;

  public BankAccount(int cashBalance, String password, int resetCode) {
    this.cashBalance = cashBalance;
    this.password = password;
    this.resetCode = resetCode;
    this.passwordRetries = 0;

    this.loggedIn = new LoggedIn(this);
    this.loggedOut = new LoggedOut(this);
    this.suspended = new Suspended(this);

    this.bankAccountState = loggedIn; // 초기 상태: 로그인 상태
  }

  public void setState(BankAccountState state) {
    this.bankAccountState = state;
  }

  public BankAccountState getState() {
    return this.bankAccountState;
  }

  public BankAccountState getLoggedInState() {
    return this.loggedIn;
  }

  public BankAccountState getLoggedOutState() {
    return this.loggedOut;
  }

  public BankAccountState getSuspendedState() {
    return this.suspended;
  }

  public StateInfo login(String password) {
    return this.bankAccountState.login(password);
  }

  public StateInfo logout() {
    return this.bankAccountState.logout();
  }

  public StateInfo unlock(int resetCode) {
    return this.bankAccountState.unlock(resetCode);
  }

  public StateInfo withdrawMoney(int amount) {
    return this.bankAccountState.withdrawMoney(amount);
  }

  public void setCashBalance(int amount) {
    this.cashBalance = amount;
  }

  public int getCashBalance() {
    return this.cashBalance;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPasswordRetries(int passwordRetries) {
    this.passwordRetries = passwordRetries;
  }

  public int getPasswordRetries() {
    return this.passwordRetries;
  }

  public int getResetCode() {
    return this.resetCode;
  }
}

// LoggedIn 상태
class LoggedIn implements BankAccountState {
  private final BankAccount bankAccount;

  public LoggedIn(BankAccount bankAccount) {
    this.bankAccount = bankAccount;
  }

  @Override
  public StateInfo login(String password) {
    return StateInfo.LOGGED_IN; // 이미 로그인 상태이므로 변경 없음
  }

  @Override
  public StateInfo logout() {
    bankAccount.setState(bankAccount.getLoggedOutState());
    return StateInfo.LOGGED_OUT;
  }

  @Override
  public StateInfo unlock(int resetCode) {
    return StateInfo.LOGGED_IN; // 잠긴 상태가 아니므로 무시
  }

  @Override
  public StateInfo withdrawMoney(int amount) {
    if (amount > bankAccount.getCashBalance()) {
      return StateInfo.LOGGED_IN; // 잔액 부족, 상태 유지
    }
    bankAccount.setCashBalance(bankAccount.getCashBalance() - amount);
    return StateInfo.LOGGED_IN;
  }
}

// LoggedOut 상태
class LoggedOut implements BankAccountState {
  private final BankAccount bankAccount;

  public LoggedOut(BankAccount bankAccount) {
    this.bankAccount = bankAccount;
  }

  @Override
  public StateInfo login(String password) {
    if (password.equals(bankAccount.getPassword())) {
      bankAccount.setPasswordRetries(0);
      bankAccount.setState(bankAccount.getLoggedInState());
      return StateInfo.LOGGED_IN;
    }

    bankAccount.setPasswordRetries(bankAccount.getPasswordRetries() + 1);
    if (bankAccount.getPasswordRetries() >= 3) {
      bankAccount.setState(bankAccount.getSuspendedState());
      return StateInfo.SUSPENDED;
    }
    return StateInfo.LOGGED_OUT;
  }

  @Override
  public StateInfo logout() {
    return StateInfo.LOGGED_OUT; // 이미 로그아웃 상태
  }

  @Override
  public StateInfo unlock(int resetCode) {
    return StateInfo.LOGGED_OUT; // 로그아웃 상태에서 unlock 불가능
  }

  @Override
  public StateInfo withdrawMoney(int amount) {
    return StateInfo.LOGGED_OUT; // 로그아웃 상태에서는 출금 불가능
  }
}

// Suspended 상태
class Suspended implements BankAccountState {
  private final BankAccount bankAccount;

  public Suspended(BankAccount bankAccount) {
    this.bankAccount = bankAccount;
  }

  @Override
  public StateInfo login(String password) {
    return StateInfo.SUSPENDED; // 로그인 불가능
  }

  @Override
  public StateInfo logout() {
    return StateInfo.SUSPENDED; // 로그아웃 불가능
  }

  @Override
  public StateInfo unlock(int resetCode) {
    if (resetCode == bankAccount.getResetCode()) {
      bankAccount.setPasswordRetries(0);
      bankAccount.setState(bankAccount.getLoggedOutState());
      return StateInfo.LOGGED_OUT;
    }
    return StateInfo.SUSPENDED;
  }

  @Override
  public StateInfo withdrawMoney(int amount) {
    return StateInfo.SUSPENDED; // 출금 불가능
  }
}
