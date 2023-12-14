package ru.sber.springjpademo.persistence.entity

import org.hibernate.annotations.NaturalId
import ru.sber.springjpademo.persistence.entity.Student
import javax.persistence.*

@Entity
@Table(name = "subject")
class Subject(
    @Id
    @GeneratedValue
    var id: Long = 0,

    @NaturalId
    var name: String,

    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_id")
    var teacher: Teacher,

    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinTable(name = "subject_student",
        joinColumns = [JoinColumn(name = "subject_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "student_id", referencedColumnName = "id")])
    var students: MutableList<Student>

){
    override fun toString(): String = "id=$id, name=$name teacher(name = ${teacher.name}) students($students) "

}