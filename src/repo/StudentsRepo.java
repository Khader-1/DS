package repo;

import java.util.List;

import actions.DeleteStudentAction;
import actions.StudentCreatedAction;
import models.Student;
import structures.BinarySearchTree;
import structures.LinkedList;

public class StudentsRepo {
    final BinarySearchTree<Student> tree = new BinarySearchTree<>();
    final LinkedList<Student> list = new LinkedList<>();

    final List<StudentCreatedAction> studentCreatedActions = new java.util.LinkedList<>();
    final List<DeleteStudentAction> deleteStudentActions = new java.util.LinkedList<>();

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void addStudentCreatedAction(StudentCreatedAction action) {
        studentCreatedActions.add(action);
    }

    public void addDeleteStudentAction(DeleteStudentAction action) {
        deleteStudentActions.add(action);
    }

    protected void notifyStudentCreated(Student student) {
        for (StudentCreatedAction action : studentCreatedActions) {
            action.onStudentCreated(student);
        }
    }

    protected void notifyDeleteStudent() {
        for (DeleteStudentAction action : deleteStudentActions) {
            action.onDelete();
        }
    }

    public void addFirst(Student student) {
        list.insertFirst(student);
        tree.insert(student);
        notifyStudentCreated(student);
    }

    public void addLast(Student student) {
        list.insertLast(student);
        tree.insert(student);
        notifyStudentCreated(student);
    }

    public void addSecond(Student student) {
        if (list.isEmpty()) {
            addFirst(student);
        } else {
            list.insertAfter(list.first(), student);
            tree.insert(student);
        }
        notifyStudentCreated(student);
    }

    public void removeFirst() {
        if (!list.isEmpty()) {
            tree.deleteNode(list.first().value);
            list.removeFirst();
        }
        notifyDeleteStudent();
    }

    public void removeLast() {
        if (!list.isEmpty()) {
            tree.deleteNode(list.last().value);
            list.removeLast();
        }
        notifyDeleteStudent();
    }

    public void removeSecond() {
        if (!list.isEmpty()) {
            tree.deleteNode(list.first().next.value);
            list.removeAfter(list.first());
        }
        notifyDeleteStudent();
    }

    public List<Student> inOrderList() {
        return tree.inorderTraversal();
    }

    public List<Student> preOrderList() {
        return tree.preorderTraversal();
    }

    public List<Student> postOrderList() {
        return tree.postorderTraversal();
    }

    public List<Student> unSorted() {
        return list.toList();
    }

    public List<Student> sorted() {
        List<Student> sorted = list.toList();
        sorted.sort((a, b) -> {
            if (a.total > b.total) {
                return -1;
            } else if (a.total < b.total) {
                return 1;
            } else {
                return 0;
            }
        });
        return sorted;
    }

}
