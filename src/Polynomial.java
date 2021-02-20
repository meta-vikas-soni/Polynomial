import java.util.Scanner;


public class Polynomial {
	
	private int []coeff;
	private int []expo;
	private int degree;
	Scanner input = new Scanner(System.in);
	
	private void inputPoly(int n){
		int i;
		System.out.println("Enter highest power of polynomial "+n+" : ");
		degree=input.nextInt();
		coeff = new int[degree+1];
		expo = new int[degree+1];
		for(i=degree;i>=0;i--){
			expo[degree-i]=i;
			System.out.println("Enter coefficient for degree "+i+" : ");
			coeff[degree-i]=input.nextInt();
		}
		System.out.print("\n");
	}
	//printing polynomial
	private void displayPoly(int n){
		int i;
		System.out.println("Polynomial "+n+" :");
		for(i=0;i<=degree;i++){
			if(i==degree){
				System.out.print(coeff[i]);
			}else{
				System.out.print(coeff[i]+"X"+expo[i]+"+");
			}
		}
		System.out.print("\n");
	}
	//returning degree of polynomial
	private int getDegree(){
		return degree;
	}
	//Adding polynomial
	private void addPoly(Polynomial p1, Polynomial p2){
		int lenP1=p1.expo.length;
		int lenP2=p2.expo.length;
		int i,temp;
		
		//setting highest degree
		if(lenP1>lenP2){
			degree=lenP1-1;
		}else{
			degree=lenP2-1;
		}
		coeff = new int[degree+1];
		expo = new int[degree+1];
		temp=degree;
		for(i=degree;i>=0;i--){
			expo[degree-i]=i;
		}
		//adding
		if(lenP1>lenP2){
			//adding extra elements
			for(i=degree;i>=lenP2;i--){
				coeff[degree-i]=p1.coeff[degree-i];
			}
			//adding rest elements
			for(i=degree-(lenP1-lenP2);i>=0;i--){
				coeff[degree-i]=p1.coeff[degree-i]+p2.coeff[degree-i-1];				
			}
		}else if(lenP1<lenP2){
			//adding extra elements
			for(i=degree;i>=lenP1;i--){
				coeff[degree-i]=p2.coeff[degree-i];
			}
			//adding rest elements
			for(i=degree-(lenP2-lenP1);i>=0;i--){
				coeff[degree-i]=p2.coeff[degree-i]+p1.coeff[degree-i-1];				
			}
		}else{
			for(i=degree;i>=0;i--){
				coeff[degree-i]=p1.coeff[degree-i]+p2.coeff[degree-i];
			}
		}
	}
	//evaluating polynomial
	private int evalPoly(){
		int n=coeff.length,i;
		int result = coeff[0]; 
		//using horner method 
		for (i=1;i<n;i++) 
			result = result*degree + coeff[i]; 
		return result; 
	} 
	//multiply polynomial
	private void mulPoly(Polynomial p1,Polynomial p2){
		int m=p1.coeff.length,n=p2.coeff.length;
		int []prod=new int[m+n-1];
		int i,j;
		//initialize the prod with 0 
		for(i=0;i<m+n-1;i++){ 
			prod[i]=0; 
		} 
		// Multiply
		for(i=0;i<m;i++){
			for (j=0;j<n;j++){ 
				prod[i + j] += p1.coeff[i]*p2.coeff[j]; 
			} 
		}
		//assigning to result poly
		degree=prod.length-1;
		coeff = new int[degree+1];
		expo = new int[degree+1];
		for(i=degree;i>=0;i--){
			expo[degree-i]=i;
			coeff[degree-i]=prod[degree-i];
		}
	}

	public static void main(String[] args) {
		Polynomial p1 = new Polynomial();
		Polynomial p2 = new Polynomial();
		//input polynomial
		p1.inputPoly(1);
		p2.inputPoly(2);
		//display polynomial and degree
		p1.displayPoly(1);
		System.out.println("Degree: "+p1.getDegree());
		System.out.println("Evaluation: "+p1.evalPoly()+"\n");
		
		p2.displayPoly(2);
		System.out.println("Degree: "+p2.getDegree());
		System.out.println("Evaluation: "+p2.evalPoly()+"\n");
		
		//add polynomial
		Polynomial p3 = new Polynomial();
		p3.addPoly(p1,p2);
		p3.displayPoly(3);
		System.out.println("Degree: "+p3.getDegree());
		System.out.println("Evaluation: "+p3.evalPoly()+"\n");
		
		//multiply polynomial
		Polynomial p4 = new Polynomial();
		p4.mulPoly(p1,p2);
		p4.displayPoly(4);
		System.out.println("Degree: "+p4.getDegree());
		System.out.println("Evaluation: "+p4.evalPoly()+"\n");
		
	}

}
