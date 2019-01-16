package database;

/**
 * Write a SQL query to find all duplicate emails in a table named Person.
 * <p>
 * +----+---------+
 * | Id | Email   |
 * +----+---------+
 * | 1  | a@b.com |
 * | 2  | c@d.com |
 * | 3  | a@b.com |
 * +----+---------+
 * For example, your query should return the following for the above table:
 * <p>
 * +---------+
 * | Email   |
 * +---------+
 * | a@b.com |
 * +---------+
 * Note: All emails are in lowercase.
 * <p>
 * 20190116
 */
public class DuplicatedEmails {
//    # having是分组（group by）后的筛选条件，分组后的数据组内再筛选
//    # where则是在分组前筛选

//    SELECT Email FROM Person
//    Group BY Email
//    HAVING count(Email)>1
}
