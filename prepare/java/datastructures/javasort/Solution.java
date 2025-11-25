package prepare.java.datastructures.javasort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class Student{
	int id; 
	String fname;
	double cgpa;
	
	Student(int id, String fname, double cgpa){
		this.id = id;
		this.fname = fname;
		this.cgpa = cgpa;
	}
}

public class Solution{
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int n = in.nextInt();
		List<Student> students = new ArrayList<>();
		
		for (int i = 0; i < n; i++) {
			int id = in.nextInt();
			String name = in.next();
			double cgpa = in.nextDouble();
		     students.add(new Student(id, name, cgpa)); // Add to list
		}
		
		Comparator<Student> cmp = (a,b) -> {
			int byCgpa = Double.compare(b.cgpa, a.cgpa);
			
			if(byCgpa != 0) return byCgpa;
			
			int byName = a.fname.compareTo(b.fname);
			
			if(byName != 0) return byName;
			
			return Integer.compare(a.id, b.id);
			
			
		};
		
		Collections.sort(students, cmp);
		
		for(Student s: students) {
			System.out.println(s.fname);
		}
		in.close();
	}
}