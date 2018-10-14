import edu.princeton.cs.algs4.WeightedQuickUnionUF;



public class Percolation {

    private WeightedQuickUnionUF wqu;
    private WeightedQuickUnionUF wquTop;
    private int rowNum;
    private boolean[]grid;
    private int openCount =0;
    private int bottom;

    public Percolation(int rowNum){
        this.rowNum = rowNum;
         grid = new boolean[(rowNum*rowNum)];
        wqu = new WeightedQuickUnionUF((rowNum*rowNum)+1);
        wquTop = new WeightedQuickUnionUF((rowNum*rowNum));
        bottom = this.rowNum * this.rowNum;
        for(int i=0;i<grid.length-1;i++){

            grid[i]= false;
        }
        for (int i = 0; i < this.rowNum; i++) {
            wqu.union(0,i);
            wquTop.union(0,i);
        }
      for (int i = this.rowNum * this.rowNum; i> (this.rowNum * this.rowNum)- this.rowNum; i--){
            wqu.union(this.rowNum * this.rowNum,i);


       }
    }
    public void open(int y, int x ){
        grid[convertInput(y,x)]=true;
        //up
        if(isOpen(y-1,x)) {
            wqu.union(convertInput(y, x), convertInput(y - 1, x));
            wquTop.union(convertInput(y, x), convertInput(y - 1, x));
        }
        //down
        if(isOpen((y+1),x)){
            wqu.union(convertInput(y,x),convertInput(y+1,x));
            wquTop.union(convertInput(y,x),convertInput(y+1,x));
        }
        //right
        if(x<rowNum-1 && isOpen(y,x+1)){
            wqu.union(convertInput(y,x),convertInput(y,x+1));
            wquTop.union(convertInput(y,x),convertInput(y,x+1));
        }
        //left
        if(x>0 && isOpen(y,(x-1))){
            wqu.union(convertInput(y,x),convertInput(y,x-1));
            wquTop.union(convertInput(y,x),convertInput(y,x-1));
        }



        openCount++;

    }
    public int numberOfOpenSites(){
        return openCount;
    }
    public boolean isOpen(int y, int x){
        if(convertInput(y,x)<0){

            return false;

        }else if (convertInput(y,x)>grid.length-1){

            return false;
        }
        else{

            return (grid[convertInput(y,x)]);

        }
    }
    public boolean isFull(int y, int x){

       return (isOpen(y,x)&& wquTop.connected(0,convertInput(y,x)) );
    }
    private int convertInput(int y, int x){
       return (y*rowNum)+x;
    }
    public boolean percolates(){
        return wqu.connected(0,bottom);

    }
}
