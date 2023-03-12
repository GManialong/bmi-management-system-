import java.nio.file.*;
import java.io.*;
import static java.nio.file.AccessMode.*;
import static java.nio.file.StandardOpenOption.*;
import java.util.Scanner;
import java.util.StringTokenizer ; //CRUD   Create, Read, Update, Delete
public class study {
	public static void main(String[] args) {
		Scanner strInput = new Scanner(System.in);
		String choice,cont = "y";
        while( cont.equalsIgnoreCase("y") ) {
			clrscr(); //Database -> Text File Flat File
        	System.out.println("\t\t ----------BMI Information System----------\n\n");
	        System.out.println("\t\t|          1.Add New  Record                 |");
	        System.out.println("\t\t|          2.View All  Record                |");
	        System.out.println("\t\t|          3.Search Record                   |");
	        System.out.println("\t\t|          4.Update Record                   |");
	        System.out.println("\t\t|          5.Delete Record                   |");
					System.out.println("\t\t------------------------------------------------");
	        System.out.print("\n\n");
	        System.out.println("Enter your choice: ");
	        choice = strInput.nextLine();
	        if( choice.equals("1") ) {
	        		try { AddRecord(); } catch(IOException e) { e.printStackTrace(); }

	        		try {ViewAllRecord (); } catch(IOException e) { e.printStackTrace(); }
						} else if( choice.equals("2") ) {
								try { ViewAllRecord(); } catch(IOException e) { e.printStackTrace(); }
	        } else if( choice.equals("3") ) {
	        		try { SearchRecordbyIDOrName(); } catch(IOException e) { e.printStackTrace(); }
	        } else if( choice.equals("4") ) {
	        		try { updateRecordbyID(); } catch(IOException e) { e.printStackTrace(); }
	        } else if( choice.equals("5") ) {
	        		try { DeleteRecordByID(); } catch(IOException e) { e.printStackTrace(); }
	        }
	        System.out.println("Do you want to continue? Y/N");
	        cont = strInput.nextLine();
        }
	}

	public static String PadString(String str, int num) {
		int str_size = str.length();
		for (int i = str_size; i <= num; i++) { str = str + " "; }
		return str;
	}

	public static void header() {
			System.out.println("___________________________________________________________________________________________________");
			System.out.print("|"+PadString("ID",5));
			System.out.print("|"+PadString("First Name",10));
			System.out.print("|"+PadString("Middle Name",10));
			System.out.print("|"+PadString("Last Name",10));
			System.out.print("|"+PadString("Age",5));
			System.out.print("|"+PadString("Weight",8));
			System.out.print("|"+PadString("Height",8));
			System.out.print("|"+PadString("BMI",10));
			System.out.print("|"+PadString("Status",20));



			System.out.println("|");
			System.out.println("---------------------------------------------------------------------------------------------------");
	}

	public static void showRecord(String[] data) {
			System.out.print("|"+PadString(data[0].toString(),5));
			System.out.print("|"+PadString(data[1].toString(),10));
			System.out.print("|"+PadString(data[2].toString(),10));
			System.out.print("|"+PadString(data[3].toString(),10));
			System.out.print("|"+PadString(data[4].toString(),5));
			System.out.print("|"+PadString(data[5].toString(),8));
			System.out.print("|"+PadString(data[6].toString(),8));
			System.out.print("|"+PadString(data[7].toString(),10));
			System.out.print("|"+PadString(data[8].toString(),20));



			System.out.println("|");
			System.out.println("---------------------------------------------------------------------------------------------------");

	}

	public static void AddRecord() throws IOException {
		try {
			BufferedWriter bw = new BufferedWriter( new FileWriter("employee_db.txt",true) );
			Scanner strInput = new Scanner(System.in);

			String ID, name, age,mname,lname;
			int weight;
			float height,bmi;

			System.out.print("Enter no ID: ");
			ID = strInput.nextLine();
			System.out.print("First Name: ");
			name = strInput.nextLine();
			System.out.print("Middle Name: ");
			mname= strInput.nextLine();
			System.out.print("Last Name: ");
			lname=strInput.nextLine();
			System.out.print("Enter Age: ");
			age = strInput.nextLine();
			System.out.print("Enter Weight in Kilos :");
			weight=strInput.nextInt();
			System.out.print("Enter Height in Meters :");
			height=strInput.nextFloat();
			bmi=weight/(height*height);
			String uw,n,ow,o,eo;
			uw="UNDERWEIGHT";
			n="NORMAL";
			ow="OVERWEIGHT";
			o="OBESE";
			eo="EXTREMLY OBESE";
			String stat="";



if(bmi<18){
stat=uw;
}
else if(bmi>=18&&bmi<=24){
	stat=n;
}
else if(bmi>=25&&bmi<=29){
 stat=ow;
}
else if(bmi>=29&&bmi<=34){
stat=o;
}
else if(bmi>35){
stat=eo;
}





			bw.write(ID+":::"+name+":::"+mname+":::"+lname+":::"+age+":::"+weight+":::"+height+":::"+bmi+":::"+stat);
			bw.flush();
			bw.newLine();
			bw.close();
		} catch (IOException  ex) {
				System.out.println (ex.toString());
		}
    }

	public static void ViewAllRecord() throws IOException {
		try {
			BufferedReader br = new BufferedReader( new FileReader("employee_db.txt") );
			String record;
			header();
			while( ( record = br.readLine() ) != null ) {
				String[] data = record.split(":::");
				showRecord(data);
			}
			br.close();
		} catch (IOException  ex) {
					System.out.println (ex.toString());
		}
    }

	public static void DeleteRecordByID() throws IOException {
		try {
			ViewAllRecord();
    		Scanner strInput =  new Scanner(System.in);
    		String ID, record;

    		File tempDB = new File("employee_db_temp.txt");
    		File db = new File("employee_db.txt");

    		BufferedReader br = new BufferedReader( new FileReader( db ) );
    		BufferedWriter bw = new BufferedWriter( new FileWriter( tempDB ) );

    		System.out.println("\t\t  Record\n");
				System.out.println("\n\n");
				System.out.println("\t\t Search Name or ID ");
				SearchRecordbyIDOrName();
				System.out.println("DELETE INFO");
    		System.out.println("Enter No ID to Delete: ");
    		ID =  strInput.nextLine();

    		while( ( record = br.readLine() ) != null ) {
    			String[] data = record.split(":::");
    			if( data[0].toString().equals(ID)||(data[1].toString().equals(ID)) )
    				continue;
    			bw.write(record);
    			bw.flush();
    			bw.newLine();
    		}

    		br.close();
    		bw.close();
    		db.delete();
    		tempDB.renameTo(db);
		} catch (IOException  ex) {
			System.out.println (ex.toString());
		}

    }
	public static void SearchRecordbyIDOrName() throws IOException {
		try {
    		String search,record;
    		Scanner strInput = new Scanner(System.in);

    		BufferedReader br = new BufferedReader( new FileReader("employee_db.txt") );

    		System.out.println("\t\t Search Record\n");

    		System.out.println("Enter the  ID or Name: ");
    		search = strInput.nextLine();

    		header();
    		int i = 0;
    		while( ( record = br.readLine() ) != null ) {
				String[] data = record.split(":::");

    			if( data[0].toString().equals(search) || data[1].toString().contains(search)) {
    				showRecord(data); i++;
					}	else if(data[2].toString().equals(search)||data[3].toString().equals(search)){
								showRecord(data); i++;
						}else if(data[4].toString().equals(search)||data[5].toString().equals(search)){
								showRecord(data); i++;
						}else if(data[6].toString().equals(search)||data[7].toString().equals(search)){
									showRecord(data); i++;
						}else if(data[8].toString().equals(search)){
										showRecord(data); i++;
								}

    		}
			if (i==0) { System.out.println("No Record found on search data :"+search); }


    		br.close();

    	} catch (IOException  ex) {
					System.out.println (ex.toString());
		}

    }

	public static void updateRecordbyID() throws IOException {
		int op;
		Scanner perData = new Scanner(System.in);
		System.out.println("OPTIONS :");
		System.out.println("1.UPDATE PER LINE");
		System.out.println("2.UPDATE PER DATA");
		op=perData.nextInt();

		try {
			ViewAllRecord();
    		String newName="", newAge="", record, ID,record2,nmname="",nlname="";
				int nweight=0;
				float nheight=0,nbmi=0;
				Scanner strInput = new Scanner(System.in);
				File db = new File("employee_db.txt");
    		File tempDB = new File("employee_db_temp.txt");


    		BufferedReader br = new BufferedReader( new FileReader(db) );
    		BufferedWriter bw = new BufferedWriter( new FileWriter(tempDB) );


    		System.out.println("\t\t Update Record\n\n");
		   System.out.println("Enter no ID : ");
	    	ID = strInput.nextLine();
	    	header(); int i = 0;
			while( ( record = br.readLine() ) != null ) {
				String[] data = record.split(":::");
    			if( data[0].toString().equals(ID)||data[1].toString().equals(ID)) {
			    	showRecord(data); i++;
				}else if(data[2].toString().equals(ID)||data[3].toString().equals(ID)){
						showRecord(data); i++;
				}else if(data[4].toString().equals(ID)||data[5].toString().equals(ID)){
						showRecord(data); i++;
				}else if(data[6].toString().equals(ID)||data[7].toString().equals(ID)){
							showRecord(data); i++;
				}else if(data[8].toString().equals(ID)){
								showRecord(data); i++;
						}

			}
	    	br.close();

			if (op==1) {
				System.out.println("Enter the new First Name: ");
				newName = strInput.nextLine();
				System.out.println("Enter the new Middle Name: ");
				nmname = strInput.nextLine();
				System.out.println("Enter the new Last Name: ");
				nlname = strInput.nextLine();
				System.out.println("Enter the new Age: ");
				newAge = strInput.nextLine();
				System.out.println("Enter the new Weight in Kilos : ");
				nweight = strInput.nextInt();
				System.out.println("Enter the new Height in Meters : ");
				nheight = strInput.nextFloat();
				nbmi=nweight/(nheight*nheight);
				String uw,n,ow,o,eo;
				uw="UNDERWEIGHT";
				n="NORMAL";
				ow="OVERWEIGHT";
				o="OBESE";
				eo="EXTREMLY OBESE";
				String stat="";


				if(nbmi<18){
					stat=uw;
				}
				else if(nbmi>=18&&nbmi<=24){
					stat=n;
				}
				else if(nbmi>=25&&nbmi<=29){
				 stat=ow;
				}
				else if(nbmi>=29&&nbmi<=34){
				stat=o;
				}
				else if(nbmi>35){
				stat=eo;
				}

				BufferedReader br2 = new BufferedReader( new FileReader(db) );

				while( (record2 = br2.readLine() ) != null ) {
					String[] data = record2.split(":::");
					if(data[0].toString().equals(ID)) {
						bw.write(ID+":::"+newName+":::"+nmname+":::"+nlname+":::"+newAge+":::"+nweight+":::"+nheight+":::"+nbmi+":::"+stat);
					} else {
						bw.write(record2);
					}
					bw.flush();
					bw.newLine();
				}

				bw.close();
				br2.close();
				db.delete();
				boolean success = tempDB.renameTo(db);
			}else if(op==2){
     	Scanner pathFinder = new Scanner(System.in);
				int find;
				nbmi=nweight/(nheight*nheight);
				String uw,n,ow,o,eo;
				uw="UNDERWEIGHT";
				n="NORMAL";
				ow="OVERWEIGHT";
				o="OBESE";
				eo="EXTREMLY OBESE";
				String stat="";
				String c;
				File aa = new File("employee_db.txt");
				BufferedReader bb = new BufferedReader( new FileReader(aa) );
				int a = 1;
				String s=String.valueOf(nweight);
				String f=String.valueOf(nheight);
				String k=String.valueOf(nbmi);
				String[] data = {};
				while (a<=i){
					c = bb.readLine();
				data = c.split(":::");

					newName = data[1];
					nmname = data[2];
					nlname = data[3];
					newAge = data[4];
					s = data[5];
					f = data[6];
					k  = data[7];
					stat = data[8];
					a++;
				 }
				bb.close();

				if(nbmi<18){
					stat=uw;
				}
				else if(nbmi>=18&&nbmi<=24){
					stat=n;
				}
				else if(nbmi>=25&&nbmi<=29){
				 stat=ow;
				}
				else if(nbmi>=29&&nbmi<=34){
				stat=o;
				}
				else if(nbmi>35){
				stat=eo;
				}
				System.out.println("Enter What you will Update?");
				System.out.println("1.First Name");
				System.out.println("2.Middle Name");
				System.out.println("3.Last Name");
				System.out.println("4.Age");
			//	System.out.println("5.Weight");
			//	System.out.println("6.Height");

				find=pathFinder.nextInt();

				if(find==1){
					System.out.println("Enter the new First Name: ");
					newName = strInput.nextLine();
				}else if(find==2){
					System.out.println("Enter the new Middle Name: ");
					nmname = strInput.nextLine();
				}else if(find==3){
					System.out.println("Enter the new Last Name: ");
					nlname = strInput.nextLine();
				}else if(find==4){
					System.out.println("Enter the new Age: ");
					newAge = strInput.nextLine();
				}else if(find==5){
					System.out.println("Enter the new Weight in Kilos : ");
					nweight = strInput.nextInt();
				}else if(find==6){
					System.out.println("Enter the new Height in Meters : ");
					nheight = strInput.nextFloat();
				}

								BufferedReader br2 = new BufferedReader( new FileReader(db) );

								while( (record2 = br2.readLine() ) != null ) {
									 data = record2.split(":::");
									if(data[0].toString().equals(ID)) {
										bw.write(ID+":::"+newName+":::"+nmname+":::"+nlname+":::"+newAge+":::"+s+":::"+f+":::"+k+":::"+stat);
									} else {
										bw.write(record2);
									}
									bw.flush();
									bw.newLine();
								}

								bw.close();
								br2.close();
								db.delete();
								boolean success = tempDB.renameTo(db);
			}


		} catch (IOException  ex) {
					System.out.println (ex.toString());
		}

    }

	public static void clrscr(){
    //Clears Screen in java
		try {
			if (System.getProperty("os.name").contains("Windows"))
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			else
				//Runtime.getRuntime().exec("clear");
				new ProcessBuilder("clear").inheritIO().start().waitFor();
		} catch (IOException | InterruptedException ex) {}
	}
}
