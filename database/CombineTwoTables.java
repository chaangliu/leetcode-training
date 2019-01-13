package database;

/**
 * Table: Person
 *
 * +-------------+---------+
 * | Column Name | Type    |
 * +-------------+---------+
 * | PersonId    | int     |
 * | FirstName   | varchar |
 * | LastName    | varchar |
 * +-------------+---------+
 * PersonId is the primary key column for this table.
 * Table: Address
 *
 * +-------------+---------+
 * | Column Name | Type    |
 * +-------------+---------+
 * | AddressId   | int     |
 * | PersonId    | int     |
 * | City        | varchar |
 * | State       | varchar |
 * +-------------+---------+
 * AddressId is the primary key column for this table.
 *
 *
 * Write a SQL query for a report that provides the following information for each person in the Person table, regardless if there is an address for each of those people:
 *
 * FirstName, LastName, City, State
 *
 * 20190112
 */
public class CombineTwoTables {
//    #http://www.w3school.com.cn/sql/sql_join_left.asp
//    #LEFT JOIN 关键字会从左表 (table_name1) 那里返回所有的行，即使在右表 (table_name2) 中没有匹配的行。


//    select FirstName, LastName, City, State from Person
//    left join Address
//    on Person.PersonId = Address.PersonId;
}
