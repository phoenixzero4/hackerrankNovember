package prepare.java.datastructures.javasort;

/**
 * 
 */
package prepare.java.datastructures.javasort;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

class Student implements Comparable<Student>{
	private int id;
	private String fname;
	private double cgpa;
	
	public Student(int id, String fname, double cgpa) {
		super();
		this.id = id;
		this.fname = fname;
		this.cgpa = cgpa;
	}
	public int getId() {
		return id;
	}
	public String getFname() {
		return fname;
	}
	public double getCgpa() {
		return cgpa;
	}
	
	public int compareTo(Student two) {
		double cgpa1 = this.getCgpa();
		double cgpa2 = two.getCgpa();
		
		if(cgpa1 < cgpa2) {
			return 1;
		}else if(cgpa2 < cgpa1) {
			return -1;
		}else {
			if(this.getFname().compareTo(two.getFname()) ==  0) {
				return this.getId() - two.getId();
			}else {
				return this.getFname().compareToIgnoreCase(two.getFname());
			}
		}
	}
}

/*
 * class StudentComparator implements Comparator<Student>{ public int
 * compare(Student s1, Student s2) {
 * 
 * double cgpa1 = s1.getCgpa(); double cgpa2 = s2.getCgpa();
 * 
 * if(cgpa1 < cgpa2) { return -1; }else if(cgpa2 < cgpa1) { return 1; }else {
 * if(s1.getFname().compareTo(s2.getFname()) == 0) { return s1.getId() -
 * s2.getId(); }else { return s1.getFname().compareTo(s2.getFname()); } } }
 * 
 * }
 */

//Complete the code
public class Solution2
{
	public static void main(String[] args) throws Exception{
	//	Scanner in = new Scanner(System.in);
		
		File file = new File("100.txt");
		String path = file.getAbsolutePath();
		
		System.err.println(path);
		File f = new File(path);
		Scanner in = new Scanner(f);
		int n = in.nextInt();
		
		List<Student> studentList = new ArrayList<Student>();
		for(int i = 0; i < n; i++) {
			int id = in.nextInt();
			String fname = in.next();
			double cgpa = in.nextDouble();
			
			
//			System.out.printf("id: %d name: %s cgpa: %f\n",  id, fname, cgpa);
			Student st = new Student(id, fname, cgpa);
			studentList.add(st);
			
			
		}
      in.close();
     
 
      	
      	TreeSet<Student> list = new TreeSet<Student>();
      	
      	for(int i = 0; i < studentList.size()-1; i++) {
      		Student one = studentList.get(i);
      		for(int j = i+1; j < studentList.size(); j++) {
      			Student two = studentList.get(j);
      			
      			if(one.compareTo(two) < 0) {
      				list.add( one);
      				list.add( two);
      			}else if(one.compareTo(two) > 0){
      				list.add( one);
      				list.add( two);
      			}
      		}
      	}
      	
      	for(Student s: list) {
      		System.out.println(s.getFname());
      	}
	}
}




