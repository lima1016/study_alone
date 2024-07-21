
SELECT DISTINCT c.group_name                                                            as name
              , COUNT(c.group_name)                                                     as all_test_cases
              , (length(c.status) - length(replace(c.status, 'OK', '')))                as passed_test_cases
              , g.test_value * (length(c.status) - length(replace(c.status, 'OK', ''))) as total_value
from test_cases c
         inner join test_groups g on c.group_name = g.name
group by c.status, c.group_name, g.test_value
