# StateStreet

1) "What if we want to record course scores"
   We connected Student and Course entities directly. If we want to add a property to the relationship then we need to:

   1. Add a new field (score) to the student_courses table
   
   2. Create a new entity class for the student_courses table (StudentCourseEntity)
   
   3. Express the many-to-many relationship with two relationships:
   
      * One-to-Many: (StudentEntity(one) - to - StudentCourseEntity(many))
      * One-to-Many: (CourseEntity(one)  - to - StudentCourseEntity(many))
   
2) "How to find all students who don’t register for a given course"
    
    See findNotEnrolledStudents() method.

    In SQL we can do it with just single query:

    Select [All students]
    Minus
    Select [Students enrolled in the given course]

    However, the Hibernate implementation of JPA does not support operation MINUS. (EclipseLink does with 'EXCEPT' keyword).

    In order to keep the code JPA implementation agnostic I implemented it with two steps:
    1. Find all students
    2. Remove students enrolled in the course from the set

    PS. This is quick implementation that assumes all student names are unique (Which is not realistic). 
    A more correct implementation should rely on student id.

3) The problem described in item 2) represents pros and cons of JPA: 
   On one hand you have an ORM tool to construct DB access code in a fast and portable way. 
   On the other hand JPQL language is limited when compared to plain SQL.

   JPA provides a hook to implement a query in native SQL. This may reduce code portability.
