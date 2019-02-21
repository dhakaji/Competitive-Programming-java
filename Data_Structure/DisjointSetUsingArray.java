class DisjointSetUsingArray{
	private int []parent;
	private int []rank;
	private int count=0;
	DisjointSetUsingArray(int no){
		parent=new int[no];
		rank=new int[no];
		for(int i=0;i<no;i++)		
		{
			parent[i]=-1;		
			//rank[i]=0;
		}
	}
	public int numberOfSets(){
		return count;
	}
	public boolean isRepresentative(int a){
	    if(parent[a]==a)
	    return true;
	    return false;
	}
	public void makeSet(int a){
		if(parent[a]!=-1)
			return ;
		parent[a]=a;
		rank[a]=0;
		count++;
	}
	public boolean union(int a,int b){
		if(parent[a]==-1 || parent[b]==-1)
			return false;
		int root1=findSet(a);
		int root2=findSet(b);
		if(root1==root2)
			return false;
		if(rank[root1]>=rank[root2] ){
			if(rank[root1]==rank[root2])
				rank[root1]+=1;
			parent[root2]=root1;
		}
		else
			parent[root1]=root2;
		count--;
		return true;
	}
	public int findSet(int a){
		if(parent[a]==a)
			return a;
		parent[a]=findSet(parent[a]);
		return parent[a];
	}
}