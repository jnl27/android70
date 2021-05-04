package chess;

import android.util.Log;

import java.util.*;
import pieces.*;
import static pieces.King.castledK;
import static pieces.King.castledQ;
import com.example.androidchess70.MainActivity.*;

/**
 * Chess class contains the main method and is where the game is running from and ends.
 * @author Param Thakker
 * @author Jonathan Lu
 */

public class Chess {

	/**
	 * Board the chessBoard by which the game is played
	 */
	/**
	 * boolean whiteTurn to display if it is white's turn or not
	 */
	static boolean whiteTurn = true;
	/**
	 * boolean askDraw to display whether a draw has been proposed by a player.
	 */
	static boolean askDraw=false;


	/*public static void main(String[] args) {
		setUpGame();
		Scanner sc = new Scanner(System.in);
		String input = "";


		while (true) { //while not checkmate

			if (whiteTurn) {
				System.out.println();
				boolean check=isKingInCheck(0,findKingPosition(0));
				if (check) {
					boolean checkmate=isCheckMate(0);
					if (checkmate) {
						System.out.println("Checkmate");
						System.out.println("Black wins");
						System.exit(1);
					}
					else {
						System.out.println("Check");
					}
				}
				System.out.print("White's move: ");

			}
			else {
				System.out.println();
				boolean check=isKingInCheck(1,findKingPosition(1));
				if (check) {
					boolean checkmate=isCheckMate(1);
					if (checkmate) {
						System.out.println("Checkmate");
						System.out.println("White wins");
						System.exit(1);
					}
					else {
						System.out.println("Check");
					}
				}

				System.out.print("Black's move: ");
			}


			input = sc.nextLine();
			newBoardState(input);
		}

	}*/

	
	/**
	 * setUpGame is a method which resets/sets up the chessboard and the pieces in the right place
	 * It takes no parameters and does not have a return type.
	 */
	public static void setUpGame(Board chessBoard) {
		chessBoard = new Board();
		chessBoard.drawBoard();
	}
	
	
	/**
	 * newBoardState method is used to draw the new state of the board after parsing the user input
	 *
	 * @param input	   The user input to parse
	 * The method does not have a return type.
	 */
	
	public static void newBoardState(String input, Board chessBoard) {
		String[] tokens = input.trim().toLowerCase().split("\\s+");
		if (tokens.length == 0 || tokens.length > 3) {
			System.out.println("Illegal move, try again");
			if (whiteTurn) {
				System.out.println("White's move: ");
			}
			else {
				System.out.println("Black's move: ");
			}	
		}
		else if (tokens.length == 1) {
			if (tokens[0].equals("resign")) {
				if (whiteTurn) {
					System.out.println("Black wins");
				}
				else {
					System.out.println("White wins");
				}
				System.exit(1);
			}
			else if (tokens[0].equals("draw")) {
				if (askDraw) {
					System.out.println("Draw");
					System.exit(1);
				}
				else {
					System.out.println("Opponent has not asked for a draw yet");
				}
			}
		}
		else { //correct inputs
			//move tokens[0] to tokens[1] if a valid move
			int xfrom = Math.abs('a'-tokens[0].charAt(0));
			int yfrom = Math.abs(8-Character.getNumericValue(tokens[0].charAt(1)));
			int xto = Math.abs('a'-tokens[1].charAt(0));
			int yto = Math.abs(8-Character.getNumericValue(tokens[1].charAt(1)));
			int currColor = whiteTurn ? 0 : 1;
			Spot currSpot = chessBoard.grid[xfrom][yfrom];
			Spot destSpot = chessBoard.grid[xto][yto];

			if (currSpot.isEmpty()){
				System.out.println("Illegal move, try again");
			}else if(currSpot.getPiece().getColor() == currColor && currSpot.getPiece().validMoveWithoutCheck(chessBoard, currSpot, destSpot) && chessBoard.isPathEmpty(currSpot, destSpot)) {
				boolean pawnPromo = false;
				boolean enPassant = false;
				//valid move
				//CASTLING
				if (castledK) {
					if (whiteTurn){
						//move rook only as king moving is handled below
						chessBoard.grid[5][7].setPiece(chessBoard.grid[7][7].getPiece());
						chessBoard.grid[7][7].setPiece(null);
					}else{
						chessBoard.grid[5][0].setPiece(chessBoard.grid[7][0].getPiece());
						chessBoard.grid[7][0].setPiece(null);
					}
					castledK = false;
				}
				if (castledQ) {
					if (whiteTurn){
						//same thing move rook only
						chessBoard.grid[3][7].setPiece(chessBoard.grid[0][7].getPiece());
						chessBoard.grid[0][7].setPiece(null);
					}else{
						chessBoard.grid[3][0].setPiece(chessBoard.grid[0][0].getPiece());
						chessBoard.grid[0][0].setPiece(null);
					}
					castledQ = false;
				}
				//remove piece from old Spot
			
				//System.out.println("selected piece and color: " + currSpot.getPiece().getPieceName() + " " + currSpot.getPiece().getColor());
				ChessPiece mover = currSpot.getPiece();
				//System.out.println("mover's previous y change: " + mover.getPreviousChange());
				ChessPiece destPiece=destSpot.getPiece();

				if (mover.getPieceName().substring(1).equals("p")) { //pawn promo potential or enpassant
					Pawn currPawn = (Pawn) mover;
					if (whiteTurn) {
						if (currSpot.getYCoordinate() == 1){
							char toPromo = tokens.length>2 ? tokens[2].charAt(0) : 'Q';
							pawnPromotion(currSpot, destSpot, toPromo, 0);
						//	System.out.println("white's pawn has been promoted to " + toPromo);
							pawnPromo = true;
						}else if (currPawn.getEnPassant()){
							enPassant = true;
							//System.out.println("\nBAM! " + chessBoard.grid[xto][yto+1].getPiece().getPieceName() + " was enPassant captured by " + mover.getPieceName() + " @ (" + xto + ", " + (yto-1) +")");
							chessBoard.grid[xto][yto+1].setPiece(null);
							destSpot.setPiece(mover);
						}
					}else{
						if (currSpot.getYCoordinate() == 6){
							char toPromo = tokens.length>2 ? tokens[2].charAt(0) : 'Q';
							pawnPromotion(currSpot, destSpot, toPromo, 1);
						//	System.out.println("black's pawn has been promoted to " + toPromo);
							pawnPromo = true;
						}else if (currPawn.getEnPassant()){
							enPassant = true;
							//System.out.println("\nBAM! " +chessBoard.grid[xto][yto-1].getPiece().getPieceName() + " was enPassant captured by " + mover.getPieceName() + " @ (" + xto + ", " + (yto+1) +")");
							chessBoard.grid[xto][yto-1].setPiece(null);
							destSpot.setPiece(mover);
						}
					}
				}

				if (!pawnPromo && !enPassant) {
					//check new Spot for enemy Piece, if so then remove
					if (destSpot.getPiece() != null){
						//System.out.println("\nBAM! " + destSpot.getPiece().getPieceName() + " was captured by " + mover.getPieceName() + " @ (" + xto + ", " + yto +")");
					}
					destSpot.setPiece(mover);
					currSpot.setPiece(null);
					//System.out.println("moved piece has previous y change of " + destSpot.getPiece().getPreviousChange());
					if (whiteTurn && isKingInCheck(0,findKingPosition(0, chessBoard), chessBoard)) {
						System.out.println("Illegal move, try again");
						currSpot.setPiece(mover);
						destSpot.setPiece(destPiece);
						return;
					}
					else if (!whiteTurn && isKingInCheck(1,findKingPosition(1, chessBoard), chessBoard)) {
						System.out.println("Illegal move, try again");
						currSpot.setPiece(mover);
						destSpot.setPiece(destPiece);
						return;
					}
					
				}
				currSpot.setPiece(null);
				

				chessBoard.drawBoard();
				whiteTurn = whiteTurn ? false : true; //switch colors
			} else {
			    //System.out.println("selected piece and color: " + currSpot.getPiece().getPieceName() + " " + (currSpot.getPiece().getColor() == 0 ? "white" : "black"));
				//System.out.println("attempted move: " + currSpot.getXCoordinate() + "," + currSpot.getYCoordinate() + " to " + destSpot.getXCoordinate() + "," + destSpot.getYCoordinate());
				System.out.println("Illegal move, try again");
				
			}
			if (tokens.length>2 && tokens[2].equals("draw?")) {
				askDraw=true;
			}

		}
	}
	/**pawnPromotion method is used to promote the pawn to a new chosen piece (Queen by default)
	 * 
	 * @param curr    The current Spot where the pawn is located
	 * @param dest    The final Spot where the pawn is attempting to move
	 * @param promo   The letter indicating what the promoted piece should be
	 * @param color   The color (black 1/white 0) of the promoted piece
	 * The method does not have a return type.
	 */
	public static void pawnPromotion(Spot curr, Spot dest, char promo, int color){
		switch (Character.toLowerCase(promo)){
			case 'r':
				//System.out.println("ROOK");
				dest.setPiece(new Rook(color));
				break;
			case 'n':
				//System.out.println("KNIGHT");
				dest.setPiece(new Knight(color));
				break;
			case 'b':
				//System.out.println("BISHOP");
				dest.setPiece(new Bishop(color));
				break;
			default: //dont have to do case Q because default covers that
				//System.out.println("QUEEN");
				dest.setPiece(new Queen(color));
		}
	}
	/**
	 * isKingInCheck method is used to detect if the King is in check
	 * 
	 * @param color   The color (black 1/white 0) of the king
	 * @param kingPosition   The position which will be checked to see if king is/will be in check
	 * @return    True if king is/will be in check, false otherwise
	 */
   	public static boolean isKingInCheck(int color, Spot kingPosition, Board chessBoard) {
		if (color==0) {   // White's move
	
		for (int i=0;i<8;i++) {
			for (int j=0;j<8;j++) {
				if (chessBoard.grid[j][i].getPiece()!=null && chessBoard.grid[j][i].getPiece().getColor()==1 && chessBoard.grid[j][i].getPiece().validMove(chessBoard, chessBoard.grid[j][i], kingPosition ) && chessBoard.isPathEmpty(chessBoard.grid[j][i], kingPosition)) {
					return true;
				}
			}
		}
		}
		else {   // Black's move
			
			for (int i=0;i<8;i++) {
				for (int j=0;j<8;j++) {
					if (chessBoard.grid[j][i].getPiece()!=null && chessBoard.grid[j][i].getPiece().getColor()==0 && chessBoard.grid[j][i].getPiece().validMove(chessBoard, chessBoard.grid[j][i], kingPosition ) && chessBoard.isPathEmpty(chessBoard.grid[j][i], kingPosition)) {
						return true;
					}
				}
			}
	}
		return false;
	}
   	/** isCheckMate method is used to detect if the King is in checkmate
   	 * 
   	 * @param color   The color (0 white/1 black) of the king
   	 * @return    True if the King is in checkmate, false otherwise
   	 */
	public static boolean isCheckMate(int color, Board chessBoard) {
		Spot kingPosition=findKingPosition(color, chessBoard);
	
		int[] xDirection=new int[] {-1,0,1,-1,1,-1,0,1};
		int[] yDirection=new int[] {-1,-1,-1,0,0,1,1,1};
		
		//if (isKingInCheck(color,kingPosition)) {
			for (int i=0;i<xDirection.length;i++) {
				int newX=kingPosition.getXCoordinate()+xDirection[i];
				int newY=kingPosition.getYCoordinate()+yDirection[i];
				if (newX<0 || newX>7 || newY<0 || newY>7) {
					continue;
				}
				Spot endPosition=chessBoard.grid[newX][newY];
				if (kingPosition.getPiece().validMoveWithoutCheck(chessBoard, kingPosition, endPosition)) {
					if (!isKingInCheck(color,endPosition, chessBoard)) {
						return false;
				}
			}
		}
			if (!validMoves(color, chessBoard)) {
				return true;
			}

		return false;
	}
	
	/** findKingPosition method is used to find the position of the king
	 * 
	 * @param color   The color (black/white) of the king
	 * @return        The Spot the King currently resides in
	 */
	public static Spot findKingPosition(int color, Board chessBoard) {
		Spot kingPosition=null;
		if (color==0) {
		for (int i=0;i<8;i++) {
			for (int j=0;j<8;j++) {
				//Log.d("ChessApp", "Now Checking Spot (" + chessBoard.grid[j][i].getXCoordinate() + "," + chessBoard.grid[j][i].getYCoordinate() +")");
				if (!chessBoard.grid[j][i].isEmpty() && chessBoard.grid[j][i].getPiece().getPieceName().equals("wK")) {
					kingPosition=chessBoard.grid[j][i];
					break;
				}
					
			}
		}
		}
		else if (color==1) {
			for (int i=0;i<8;i++) {
				for (int j=0;j<8;j++) {
					//Log.d("ChessApp", "Now Checking Spot (" + i + "," + j +")");
					if (!chessBoard.grid[j][i].isEmpty() && chessBoard.grid[j][i].getPiece().getPieceName().equals("bK")) {
						kingPosition=chessBoard.grid[j][i];
						break;
					}
						
				}
			}
		}
		return kingPosition;
	}
	
	/** validMoves method is used to check if there are ways to block the king in check
	 * 
	 * @param color  The color (black/white) of the defending pieces
	 * @return       True if there are ways to block the check, false otherwise
	 */
	
	public static boolean validMoves(int color, Board chessBoard) {
		String pieceToExclude="";
		if (color==0) {
			pieceToExclude="wK";
		}
		else {
			pieceToExclude="bK";
		}
		for (int i=0;i<8;i++) {
			for (int j=0;j<8;j++) {
				if (chessBoard.grid[j][i].getPiece()!=null && chessBoard.grid[j][i].getPiece().getColor()==color && !chessBoard.grid[j][i].getPiece().getPieceName().equals(pieceToExclude)) {
					for (int a=0;a<8;a++) {
						for (int b=0;b<8;b++) {
							Spot newPosition=chessBoard.grid[b][a];
							ChessPiece newPositionPiece=newPosition.getPiece();
							ChessPiece current=chessBoard.grid[j][i].getPiece();
							if (chessBoard.grid[j][i].getPiece().validMoveWithoutCheck(chessBoard, chessBoard.grid[j][i], newPosition) && chessBoard.isPathEmpty(chessBoard.grid[j][i], newPosition)) {
								
								
								newPosition.setPiece(current);
								chessBoard.grid[j][i].setPiece(null);
								if (!isKingInCheck(color,findKingPosition(color, chessBoard), chessBoard)) {
									//System.out.println("The piece is " + current.getPieceName() + " and move is " + newPosition.getXCoordinate() + "," + newPosition.getYCoordinate());
;									chessBoard.grid[j][i].setPiece(current);
									newPosition.setPiece(newPositionPiece);
									return true;
								}
							}
							chessBoard.grid[j][i].setPiece(current);
							newPosition.setPiece(newPositionPiece);
						}
					}
				}
			}
		}
		
		return false;
	}
	
	
	
	
	
	/*  0  1  2  3  4  5  6  7  
	 *|bR|bN|bB|bQ|bK|bB|bN|bR| 0  j
	 *|bP|bP|bP|bP|bP|bP|bP|bP| 1
	 *|  |##|  |##|  |##|  |##| 2
	 *|##|  |##|  |##|  |##|  | 3
	 *|  |##|  |##|  |##|  |##| 4
	 *|##|  |##|  |##|  |##|  | 5
	 *|wP|wP|wP|wP|wP|wP|wP|wP| 6
	 *|wR|wN|wB|wQ|wK|wB|wN|wR| 7  */
	

}
