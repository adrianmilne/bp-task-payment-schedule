bp-task-payment-schedule
========================

Assumptions
========================
Used Short Name for Month

On each months line - I have output the bonus payment for the current month (which is actually the date in the next month). This could easily be changed such that the row for the current month shows the payment date for the previous month if required.


Requirements
========================
Sales staff get a regular monthly fixed base salary and a monthly bonus.

The base salaries are paid on the last day of the month unless that day is a Saturday or a Sunday (weekend) in which case they are paid on the Friday before.

On the 15th of every month bonuses are paid for the previous month, unless that day is weekend. In that case, they are paid the first Wednesday after the 15th.

The output of the utility should be a CSV file, containing the payment dates for the remainder of this year. 

The CSV file should contain a column for the month name, a column that contains the salary payment date for that month, and a column that contains the bonus payment date.