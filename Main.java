package bomberman;

import javax.swing.JFrame;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.*; 



public class Main extends JFrame implements Runnable{
	
	//Game Window Size
	int
	GWIDTH = 1000,
	GHEIGHT = 700;
	Dimension screenSize = new Dimension(GWIDTH, GHEIGHT);
	
	//Player Size
	int
	PWIDTH = 35,
	PHEIGHT = 35;
	
	
	//Variables
	int x,y,x1,y1,xDir,yDir,xDir1,yDir1,bx,by,ex,ey,b1x,b1y,e1x,e1y,xb,yb,o,p,rng;
	int[] blx = new int[500],bly = new int[500];
	long time;
	boolean readyToFire,readyToFire1,shot,shot1,bullet,bullet1,blow,blow1,remove,death1,death2;

	
	Random rand = new Random();

	
	//Double Buffer Image
	private Image dbImage;
	private Graphics dbg;
	
	//Dynamic Rectangles
	Rectangle[] splash = new Rectangle[50], bomb = new Rectangle[8], explosion = new Rectangle[8], timer = new Rectangle[8];
	
	
	BufferedImage sprite;
	
	public void run(){
		try{
			while(true)
			{
				move();
				move1();
				shoot();
				shoot1();
				Thread.sleep(3);
			}
		}
		catch(Exception e){
			System.out.println("Error!");
		}
	}

	//Timer
	public void shoot(){
		if(shot){timer[0].y++;}
	}public void shoot1(){
		if(shot1){timer[1].y++;}
	}
	
	//Player Movements
	public void move(){
		x += xDir;
		y += yDir;
		//Edge Collision
		if(!death1){
		if (x <= 2){x = 2;}
		if(x >= 962){x = 962;}
		if(y <= 30){y = 30;}
		if(y >= 662){y = 662;}
		}
	}
	
	public void setXDir(int xdir){
		xDir = xdir;
	}
	public void setYDir(int ydir){
		yDir = ydir;
	}
////Player 2	
	public void move1(){
		x1 += xDir1;
		y1 += yDir1;
		//Edge Collision
		if(!death2){
		if (x1 <= 2){x1 = 2;}
		if(x1 >= 962){x1 = 962;}
		if(y1 <= 30){y1 = 30;}
		if(y1 >= 662){y1 = 662;}
		}
	}
	
	public void setXDir1(int xdir){
		xDir1 = xdir;
	}
	public void setYDir1(int ydir){
		yDir1 = ydir;
	}
	
	

//Bomb Explosion
 void explode(){
		if(timer[0].y >= 400){
			explosion[0] = new Rectangle (ex,ey,35,35);
			splash[0] = new Rectangle (explosion[0].x+35, explosion[0].y, explosion[0].width,explosion[0].height);
			splash[1] = new Rectangle (explosion[0].x-35, explosion[0].y, explosion[0].width,explosion[0].height);
			splash[2] = new Rectangle (explosion[0].x+45, explosion[0].y, explosion[0].width,explosion[0].height);
			splash[3] = new Rectangle (explosion[0].x-45, explosion[0].y, explosion[0].width,explosion[0].height);
			splash[4] = new Rectangle (explosion[0].x, explosion[0].y+35, explosion[0].width,explosion[0].height);
			splash[5] = new Rectangle (explosion[0].x, explosion[0].y-35, explosion[0].width,explosion[0].height);
			splash[6] = new Rectangle (explosion[0].x, explosion[0].y+45, explosion[0].width,explosion[0].height);
			splash[7] = new Rectangle (explosion[0].x, explosion[0].y-45, explosion[0].width,explosion[0].height);		
			
			blow = true;
			bomb[0] = new Rectangle (0,0,0,0);
			}
	}
 void explode1(){
		if(timer[1].y >= 400){
			explosion[1] = new Rectangle (e1x,e1y,35,35);
			splash[8] = new Rectangle (explosion[1].x+35, explosion[1].y, explosion[1].width,explosion[1].height);
			splash[9] = new Rectangle (explosion[1].x-35, explosion[1].y, explosion[1].width,explosion[1].height);
			splash[10] = new Rectangle (explosion[1].x+45, explosion[1].y, explosion[1].width,explosion[1].height);
			splash[11] = new Rectangle (explosion[1].x-45, explosion[1].y, explosion[1].width,explosion[1].height);
			splash[12] = new Rectangle (explosion[1].x, explosion[1].y+35, explosion[1].width,explosion[1].height);
			splash[13] = new Rectangle (explosion[1].x, explosion[1].y-35, explosion[1].width,explosion[1].height);
			splash[14] = new Rectangle (explosion[1].x, explosion[1].y+45, explosion[1].width,explosion[1].height);
			splash[15] = new Rectangle (explosion[1].x, explosion[1].y-45, explosion[1].width,explosion[1].height);		
			
			blow1 = true;
			bomb[1] = new Rectangle (0,0,0,0);
			}
	}
 
//Bomb Explosion Removal
 void explodeAnimation(){
	 if(timer[0].y >= 500){
			timer[0] = new Rectangle (0,0,0,0);
			explosion[0] = new Rectangle (0,0,0,0);
			shot = false;
			readyToFire = true;
			bullet = false;
			blow = false;
			}
 }
 void explodeAnimation1(){
	 if(timer[1].y >= 500){
			timer[1] = new Rectangle (0,0,0,0);
			explosion[1] = new Rectangle (0,0,0,0);
			shot1 = false;
			readyToFire1 = true;
			bullet1 = false;
			blow1 = false;
			}
 }
	
//Keyboard Inputs	
	public class AL extends KeyAdapter {
		public void keyPressed(KeyEvent e){
			int keyCode = e.getKeyCode();
			if(keyCode == e.VK_LEFT)
			{
				setXDir(-1);
			}
			if(keyCode == e.VK_RIGHT)
			{
				setXDir(+1);
			}
			if(keyCode == e.VK_UP)
			{
				setYDir(-1);
			}
			if(keyCode == e.VK_DOWN)
			{
				setYDir(+1);
			}
			if(keyCode == e.VK_ENTER)
			{
				//Drop Bomb
				if (bomb[0] == null){
					readyToFire = true;
				}
				if (readyToFire == true){
					by = y + 17;
					bx = x + 18;
					bomb[0] = new Rectangle (bx, by, 10, 10);
					ex = bx;
					ey=by;
					bullet = true;
					shot = true; 
					timer[0] = new Rectangle(0,0,10,10);

				}
			}
			//////////////////////////////////////////////////////////////////////////////////////
			if(keyCode == e.VK_A)
			{
				setXDir1(-1);
			}
			if(keyCode == e.VK_D)
			{
				setXDir1(+1);
			}
			if(keyCode == e.VK_W)
			{
				setYDir1(-1);
			}
			if(keyCode == e.VK_S)
			{
				setYDir1(+1);
			}
			if(keyCode == e.VK_SPACE)
			{
				//Drop Bomb
				if (bomb[1] == null){
					readyToFire1 = true;
				}
				if (readyToFire1 == true){
					b1y = y1 + 17;
					b1x = x1 + 18;
					bomb[1] = new Rectangle (b1x, b1y, 10, 10);
					e1x = b1x;
					e1y=b1y;
					bullet1 = true;
					shot1 = true; 
					timer[1] = new Rectangle(0,0,10,10);

				}
			
			/////////////////////////////////////////////////////////////////////////////
			}
		}
		public void keyReleased(KeyEvent e){
			int keyCode = e.getKeyCode();
			if(keyCode == e.VK_LEFT)
			{
				setXDir(0);
			}
			if(keyCode == e.VK_RIGHT)
			{
				setXDir(0);
			}
			if(keyCode == e.VK_UP)
			{
				setYDir(0);
			}
			if(keyCode == e.VK_DOWN)
			{
				setYDir(0);
			}
			if(keyCode == e.VK_ENTER)
			{
				//Wait For Bomb To Explode
				readyToFire = false;
				
				new java.util.Timer().schedule( 
				        new java.util.TimerTask() {
				            @Override
				            public void run() {
				            	explode();
				            }
				        }, 
				        2500 
				);
				new java.util.Timer().schedule( 
				        new java.util.TimerTask() {
				            @Override
				            public void run() {
				            	explodeAnimation();
				            }
				        }, 
				        3200 
				); 
			}
			/////////////////////////////////////////////////////////////////////////////
			if(keyCode == e.VK_A)
			{
				setXDir1(0);
			}
			if(keyCode == e.VK_D)
			{
				setXDir1(0);
			}
			if(keyCode == e.VK_W)
			{
				setYDir1(0);
			}
			if(keyCode == e.VK_S)
			{
				setYDir1(0);
			}
			if(keyCode == e.VK_SPACE)
			{
				//Wait For Bomb To Explode
				readyToFire1 = false;
				
				new java.util.Timer().schedule( 
				        new java.util.TimerTask() {
				            @Override
				            public void run() {
				            	explode1();
				            }
				        }, 
				        2500 
				);
				new java.util.Timer().schedule( 
				        new java.util.TimerTask() {
				            @Override
				            public void run() {
				            	explodeAnimation1();
				            }
				        }, 
				        3200 
				); 
			}
			////////////////////////////////////////////////////
		}	
	}
	

	//Constructor
	public Main (){
		setTitle("BomberMan");
		setSize(screenSize);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.GREEN);
		addKeyListener(new AL());

	//Player Position
		x1 = 25;
		y1 = 50;
		
		x = 950;
		y= 650;

		
	//Block position initialization
		xb = 3;
		yb= 100;
		o = 0;
		p = 0;
	for(int j = 0; j < 6; j++){
			for (int i = 0; i < 29; i++){
				if( o > 1 && o < 171)
				{
					blx[o] = xb;
					bly[o] = yb;
				}
				else{
					blx[o] = -100;
					bly[o] = -100;
				}
				xb += 35;
				o++;
				p++;
			}
			yb += 100;
			xb = 3;
			p = 0;
		}

	xb = 70;
	yb= 33;
	
	for(int i = 0; i < 9; i++){
		for(int j = 0; j < 19; j++){
			int  rng = rand.nextInt(4) + 1;
			if (o > 175 && o < 343){
			blx[o] = xb;
			bly[o] = yb;
			}
			else{
				blx[o] = -100;
				bly[o] = -100;
			}
			
			yb += 35;
			o++;
			p++;
		}
		
		xb += 102;
		yb = 33;
		p = 0;
	}
	
	while(o<500){
		blx[o] = -100;
		bly[o] = -100;
		o++;
	}
	
	}
	
	
	
	// Mouse Input

	public class Mouse extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e){
			int xCoord = e.getX();
			int yCorrd = e.getY();
		}
		@Override
		public void mouseReleased(MouseEvent e){
			
		}
	}

	
//	private void init(){
//		BufferedImageLoader loader = new BufferedImageLoader();
//		BufferedImage spriteSheet = loader.loadImage("");
//		SpriteSheet ss = new SpriteSheet(spriteSheet);
//		
//	}
	

	//Double Buffer
	public void paint(Graphics g){
		dbImage = createImage(getWidth(),getHeight());
		dbg = dbImage.getGraphics();
		paintComponent(dbg);
		g.drawImage(dbImage,0,0,this);
	}
	//Drawing Rectangles
	public void paintComponent(Graphics g){
		//Default Rectangles
		Rectangle p1 = new Rectangle (x, y, PWIDTH, PHEIGHT);
		Rectangle p2 = new Rectangle (x1, y1, PWIDTH, PHEIGHT);
		Rectangle[] bl = new Rectangle[500];
		
		for (int i = 0; i < 500; i++)
		{
			bl[i] = new Rectangle (blx[i], bly[i],35,35);
			g.setColor(Color.yellow);
			g.fillRect(bl[i].x,bl[i].y, bl[i].width, bl[i].height);
		}
		
		
		g.setColor(Color.RED);
    	g.fillRect(p2.x, p2.y, p2.width, p2.height);
		g.setColor(Color.BLUE);
    	g.fillRect(p1.x, p1.y, p1.width, p1.height);
		
		//Dropping Bomb
    	if (bullet){
			g.setColor(Color.pink);
			g.fillRect(bomb[0].x, bomb[0].y, bomb[0].width,bomb[0].height);
			
		}
    	if (bullet1){
			g.setColor(Color.pink);
			g.fillRect(bomb[1].x, bomb[1].y, bomb[1].width,bomb[1].height);
			
		}
		
		//Bomb Explosion
		if (blow){
			g.setColor(Color.red);
			g.fillRect(explosion[0].x, explosion[0].y, explosion[0].width,explosion[0].height);
			
			for (int i =0; i < 8;i++)
			{
				g.fillRect(splash[i].x,splash[i].y,splash[i].width,splash[i].height);
				
				for(int j = 0; j < 400; j++){
					if(splash[i].intersects(bl[j])){
						blx[j] = -100;
						bly[j] = -100;
				    	}
					if(splash[i].intersects(p1)){
						
						death1 = true;
						x = -100;
						y = -100;
					}
					if(splash[i].intersects(p2)){
						
						death2 = true;
						x1 = -100;
						y1 = -100;
					}
				}
				}
				
		}
			if (blow1){
				g.setColor(Color.red);
				g.fillRect(explosion[1].x, explosion[1].y, explosion[1].width,explosion[1].height);
				
				for (int i =8; i < 16;i++)
				{
					g.fillRect(splash[i].x,splash[i].y,splash[i].width,splash[i].height);
					
					for(int j = 0; j < 400; j++){
						if(splash[i].intersects(bl[j])){
							blx[j] = -100;
							bly[j] = -100;
					    	}
					}
					if(splash[i].intersects(p1)){
						death1 = true;
						x = -100;
						y = -100;
					
					}
					if(splash[i].intersects(p2)){
						death2 = true;
						x1 = -100;
						y1 = -100;
						
					}
				}
		
			}

			
		//Block Collision
		for(int i = 0; i < 400; i++){
			if(p1.intersects(bl[i])){
	    		if (x >= bl[i].x-36 && x < bl[i].x-20){x=bl[i].x-36;}
	    		if (x <= bl[i].x+36 && x > bl[i].x+20){x=bl[i].x+36;}
	    		if (y >= bl[i].y-36 && y < bl[i].y-20){y=bl[i].y-36;}
	    		if (y <= bl[i].y+36 && y > bl[i].y+20){y=bl[i].y+36;}
	    	}
		}
		for(int i = 0; i < 400; i++){
			if(p2.intersects(bl[i])){
	    		if (x1 >= bl[i].x-36 && x1 < bl[i].x-20){x1=bl[i].x-36;}
	    		if (x1 <= bl[i].x+36 && x1 > bl[i].x+20){x1=bl[i].x+36;}
	    		if (y1 >= bl[i].y-36 && y1 < bl[i].y-20){y1=bl[i].y-36;}
	    		if (y1 <= bl[i].y+36 && y1 > bl[i].y+20){y1=bl[i].y+36;}
	    	}
		}
		
    	

		repaint();
	}
	
	public static void main (String[] args){
		Main game = new Main();
		Timer time = new Timer();
		 

		//Threads
		Thread t1 = new Thread(game);
		t1.start();
	}

}
