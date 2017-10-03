import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;


class bc
{
	int[][] d_2;
	ArrayList<location> l;
	public bc(int[][] d_2, ArrayList<location> l) {
		this.d_2 = d_2;
		this.l = l;
	}
	
	
}

class location
{
   
	int x;
	int y;
	int val;
	
	location(int a,int b,int c)
	{
		this.x=a;
		this.y=b;
		this.val=c;
	}
	
	location(int a,int b)
	{
		this.x = a;
		this.y = b;
		this.val = 1;
	}
}


class arr{
	
	ArrayList<location> al;
	int cnt;
	int nxtrow;
	int nxtcol;
	
	arr(arr a)
	{
		this.al = new ArrayList<location>();
		for(int i=0;i<a.al.size();i++)
			this.al.add(a.al.get(i));
		this.cnt= a.cnt;
		this.nxtrow = a.nxtrow;
		this.nxtcol = a.nxtcol;
	}
	
	arr(ArrayList<location> ini,int lizards)
	{
		this.al = ini;
		this.cnt = lizards;
		this.nxtrow=0;
		this.nxtcol=0;
	}
	
       }

public class homework {

    static int algo;
	static int lizards;
	static int[][] nursery;
	static int dim;
		
	boolean noClash(int nursery[][],int r,int c)
	{
		//tree check
		if(nursery[r][c]==2)
			return false;
		
		
		//left check
		for(int j=c;j>=0;j--)
		{
			if(nursery[r][j]==2)
			{
				break;
			}
			
			if(nursery[r][j]==1)
			{
				return false;	
			}
		}
		
		
		//upwards check
		for(int i=r;i>=0;i--)
		{
			
			if(nursery[i][c]==2)
			{
				break;
			}
			
			if(nursery[i][c]==1)
			{
				return false;
			}
		}	
			
			
		//diagonally up left
		for(int i=r,j=c;i>=0 && j>=0;i--,j--)
		{
			
			if(nursery[i][j]==2)
			{
				break;
			}
			
			if(nursery[i][j]==1)
			{
				return false;
			}
		}	
		
		//diagonally up right
		for(int i=r,j=c;i>=0 && j<dim;i--,j++)
		{
			if(nursery[i][j]==2)
			{
				break;
			}
			
			if(nursery[i][j]==1)
			{
				return false;	
			}
		}	
			
		return true;
		
	}
	
	int _count_(int [][] grid,int a,int b)
	{
		int i,j,cnt=0;
		
		//left check
		for(j=b-1;j>=0;j--)
		{
			if(grid[a][j]==1)
			{
				cnt++;
				break;
			}
			
			if(grid[a][j]==2)
				break;
			
		}
		
		//right check
		for(j=b+1;j<dim;j++)
		{
			if(grid[a][j]==1)
			{
				cnt++;
				break;
			}
			
			if(grid[a][j]==2)
				break;
			
		}
		
		//up check
		for(i=a-1;i>=0;i--)
		{
			if(grid[i][b]==1)
			{
				cnt++;
				break;
			}
			
			if(grid[i][b]==2)
				break;
			
		}
		
		//down check
		for(i=a+1;i<dim;i++)
		{
			if(grid[i][b]==1)
			{
				cnt++;
				break;
			}
			
			if(grid[i][b]==2)
				break;
			
		}
		
		//diagonal up left
		for(i=a-1,j=b-1;i>=0 && j>=0;i--,j--)
		{
			
			if(grid[i][j]==1)
			{
				cnt++;
				break;
			}
			
			if(grid[i][j]==2)
			{
				break;
			}
		}
		
		//diagonal up right
				for(i=a-1,j=b+1;i>=0 && j<dim;i--,j++)
				{
					
					if(grid[i][j]==1)
					{
						cnt++;
						break;
					}
					
					if(grid[i][j]==2)
					{
						break;
					}
				}	
		
		//diagonal down left
				for(i=a+1,j=b-1;i<dim && j>=0;i++,j--)
				{
					
					if(grid[i][j]==1)
					{
						cnt++;
						break;
					}
					
					if(grid[i][j]==2)
					{
						break;
					}
				}
				
		//diagonal down right
				for(i=a+1,j=b+1;i<dim && j<dim;i++,j++)
				{
					
					if(grid[i][j]==1)
					{
						cnt++;
						break;
					}
					
					if(grid[i][j]==2)
					{
						break;
					}
				}		
		
		
		return cnt;
	}
	
	int count_conflicts(int[][] a,ArrayList<location> x)
	{
		int count=0;
		for(int i=0;i<x.size();i++)
		{
			if(x.get(i).val==1)
			{
				count+=_count_(a,x.get(i).x,x.get(i).y);
			}
		}
		return count;
	}
	
	int[][] gen_2d_array(ArrayList<location> currplacement)
	{
		int[][] t = new int[dim][dim];
		
		int i=0;
		while(i<currplacement.size())
			t[currplacement.get(i).x][currplacement.get(i).y] = currplacement.get(i++).val;
		return t;	
	}
	
	ArrayList<location> generate_initial_position(ArrayList<location> al,ArrayList<location> z,long time)
	{
		Random r = new Random();
		int chhipkali = lizards;
		int x;
		while(chhipkali!=0 && time-System.nanoTime() > 280000000000L)
		{
			if(z.size()!=0)
			{
				x = r.nextInt(z.size());
				al.add(new location(z.get(x).x,z.get(x).y));
				z.remove(x);
				chhipkali--;
			}
		}
		if(chhipkali!=0)
		return new ArrayList<location>();
		else
			return al;
	}
	
	bc generate_next_move(ArrayList<location> al,int[][] t)
	{
		ArrayList<location> newtemp = new ArrayList<location>();
		newtemp.addAll(al);
		ArrayList<location> z = new ArrayList<location>();
		
		for(int k=0;k<dim;k++)
		{	
		for(int l=0;l<dim;l++)
		{
			if(t[k][l]==0)
			{
				z.add(new location(k,l));
			}
		}
		}		
		Random r = new Random();
		int pos,rand;
	
	//random queen	
	do
	{
		rand = r.nextInt(newtemp.size());
		
	}while(newtemp.get(rand).val!=1);
	newtemp.remove(rand);
		
	//random place
	if(z.size()!=0)
	{
		pos = r.nextInt(z.size());
		newtemp.add(new location(z.get(pos).x,z.get(pos).y));
		return new bc(gen_2d_array(newtemp),newtemp);	
	}	
	
	else
		return new bc(gen_2d_array(new ArrayList<location>()),new ArrayList<location>());
	
		
	}
	
	boolean _probability_(int n,int delta)
	{
		//double C = 2.00;
		//double d = 4.00;
		//double T = C/Math.log((d+n));
		//double T = C/n;
		double T = 100.00/n;
		double prob = Math.exp(delta/T);
		double rand = Math.random();
		if(rand<prob)
		{
			return true;
		}
		return false;
	}
	
	boolean solve_by_sa(ArrayList<location> al,ArrayList<location> z)
	{
		bc b;
		ArrayList<location> alold = new ArrayList<location>();
		ArrayList<location> alnew = new ArrayList<location>(); 
		long start_time = System.nanoTime() + 300000000000L;
		if(lizards==0)
			return true;
		alold = generate_initial_position(al,z,start_time);
		if(alold.size()==0)
			return false;
		int counter = 1;	
	    
	 while(start_time-System.nanoTime() > 15000000000L)
	 { 
		counter++;
		int[][] oldmat = new int[dim][dim];
		oldmat = gen_2d_array(alold);
		int oldconflicts = count_conflicts(oldmat,alold);
		if(oldconflicts==0)
		{
			homework.nursery = oldmat;
			return true;
		}
		b = generate_next_move(alold,oldmat);
		if(b.l.size()==0)
			return false;
		int[][] newmat = new int[dim][dim];
		newmat = b.d_2;
		alnew = b.l;
		int newconflicts = count_conflicts(newmat,alnew);
		if(newconflicts==0)
		{
			homework.nursery = newmat;
			return true;
		}
		int delta = newconflicts-oldconflicts; 
		if(delta < 0)
		{
			alold.clear();
			alold.addAll(alnew);
		}
		else
		{
			if(_probability_(counter,-delta))
			{
				alold.clear();
				alold.addAll(alnew);
			}
		}
	 } 	
		return false;
	}
	
	boolean solve_by_bfs(int nursery[][],ArrayList<location> curplacement)
	{
		arr a = new arr(curplacement,lizards);
		LinkedList<arr> queue = new LinkedList<arr>();
		queue.add(a);
		
		while(!queue.isEmpty())
		{
			arr b = (arr)queue.remove();
			homework.nursery = gen_2d_array(b.al);
			//arr temp = b;
			if(b.cnt==0)
			{
				return true;
			}	
			//nursery = gen_2d_array(b.al);	
		int ctr = 0;	
		for(int i=b.nxtrow;i<dim;i++)
		{	
			for(int j=(ctr==0?b.nxtcol:0);j<dim;j++)
			{
				
				if(noClash(homework.nursery,i,j))
				{   
					arr temp = new arr(b);
					temp.al.add(new location(i,j,1));
					temp.nxtcol=j+1;
					temp.nxtrow=i;
					temp.cnt=b.cnt-1;
					queue.add(temp);
					//break both;
				}
			}
			ctr=1;
		}
	  }	
		return false;
	}
	
	boolean solve_by_dfs(int nursery[][],int r,int c)
	{
		//if(r>=dim || c>=dim)
		//	if(lizards!=0)
		//	return false;
		  if(lizards==0)	
		   return true;
		int i;
		int j;
		int ctr = 0;
		for(i=r;i<dim;i++)
		{
	    for(j=(ctr==0)?c:0;j<dim;j++)
		{
			if(noClash(nursery,i,j))
			{
				nursery[i][j]=1;
				lizards--;
				
				if(solve_by_dfs(nursery,i,j+1)==true)
				     return true;
				if(lizards!=0)
				{
				nursery[i][j]=0;
				lizards++;}
			}
		}
	    ctr=1;
	    
		}
			
			return false;
			    
	}
	
	boolean solve(int a,ArrayList<location> b,ArrayList<location> z)
	{
		boolean pass=false;
		
		if(a==1)
		{
			if(solve_by_bfs(nursery,b))
	    		pass = true;
		}	
	    
	    else if(a==2)
	    {
	    	if(solve_by_dfs(nursery,0,0) || lizards==0)
	    	     pass=true;
	    }
	    
	    else
	    {
	    	if(solve_by_sa(b,z))
		          pass=true;
	    }
	    
		return pass;
	}
		
	
	public static void main(String[] args)
	{
		String finput = "input.txt";
		String foutput = "output.txt";
		BufferedReader input = null;
		FileReader fr = null;
		FileWriter fw =null;
		PrintWriter po = null;
		homework h = new homework();
		
		try {
     
			fr = new FileReader(finput);
			input = new BufferedReader(fr);
            fw = new FileWriter(foutput);
            po = new PrintWriter(fw);
			String CurrentLine;
            ArrayList<location> zeroplacement = new ArrayList<location>();
            ArrayList<location> curplacement = new ArrayList<location>();
            CurrentLine = input.readLine().trim();
            if(CurrentLine.equals("BFS"))
            	algo=1;
            else if(CurrentLine.equals("DFS"))
            	algo=2;
            else
            	algo=3;
            
            CurrentLine = input.readLine().trim();
            dim =  Integer.parseInt(CurrentLine);
            nursery = new int[dim][dim];
            CurrentLine = input.readLine().trim();
            lizards = Integer.parseInt(CurrentLine);

            for(int j=0;j<dim;j++)
            {
            	CurrentLine = input.readLine().trim();
               for(int i=0;i<CurrentLine.length();i++)
		       {
            	    int entry = Integer.parseInt(String.valueOf(CurrentLine.charAt(i)));
		        	nursery[j][i] = entry;
		        	if(entry==2)
		        	{
		        		curplacement.add(new location(j,i,2));
		    	    }
		        	else
		        		zeroplacement.add(new location(j,i,0));
		       }
            }
           			
		/*	  if(h.solve(algo,curplacement,zeroplacement))
			{
				
				System.out.println("OK");
				for(int i=0;i<dim;i++)
				  {
					for(int j=0;j<dim;j++)
				  	System.out.print(nursery[i][j]+" ");
					System.out.println();
			      }
			}
			else
				System.out.println("FAIL");
				
		*/	
				
            
            if(h.solve(algo,curplacement,zeroplacement))
            {
            	po.printf("%s" + "%n", "OK");
				for(int i=0;i<dim;i++)
				  {
					for(int j=0;j<dim;j++)
				  	po.printf("%d",nursery[i][j]);
				  	po.printf("%n");
			      }
            }
            else
            po.printf("%s","FAIL");	
			
							
			    po.close();	
				fr.close();
				fw.close();
				input.close();
		}
			catch(Exception e){e.printStackTrace();}
	}

}

