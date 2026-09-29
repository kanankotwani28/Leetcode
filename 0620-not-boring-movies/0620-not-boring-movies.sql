# Write your MySQL query statement below
SELECT *
FROM Cinema
WHERE ID % 2 = 1 AND DESCRIPTION <> 'BORING'
order BY RATING desc;