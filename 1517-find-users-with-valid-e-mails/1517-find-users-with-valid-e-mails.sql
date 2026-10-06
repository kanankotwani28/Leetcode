# Write your MySQL query statement below
-- VALID EMIAL --> prefix name must starts with the letter have A-Z a-z _ . -
-- domain --> @leetcode.com

SELECT user_id, name, mail
FROM Users
WHERE REGEXP_LIKE(
    mail,
    '^[A-Za-z][A-Za-z0-9_.-]*@leetcode[.]com$',
    'c'
);