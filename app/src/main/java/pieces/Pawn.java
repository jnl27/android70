package pieces;
import chess.*;
/**
 * The Pawn class is an extension of the ChessPiece class and creates a Pawn Piece.
 * @author Param Thakker
 * @author Jonathan Lu
 *
 */
public class Pawn extends ChessPiece{
	/**
	 * "p" the name used to display a pawn on the chessBoard along with color
	 */
	String pieceName="p";
	/**
	 * the pawn's color (0 white, 1 black)
	 */
	int pieceColor=0;
	/**
	 * the amount travelled vertically in the last move of the Pawn
	 */
	int previousChange=0;
	/**
	 * boolean enPassant stores whether or not an en passant move has been made
	 */
	public boolean enPassant = false;
	/**
	 * Constructor with 1 argument
	 *
	 * @param color the color of the Pawn
	 */
	public Pawn(int color) {
		this.setColor(color);
		this.pieceColor=color;
	}
	/**
	 * Constructor with 2 arguments
	 *
	 * @param color the color of the Pawn
	 * @param previousChange the amount travelled vertically in the last move of the Pawn
	 */
	public Pawn(int color, int previousChange){
		this.previousChange = previousChange;
		this.setColor(color);
		this.pieceColor=color;
	}
	public int getPreviousChange() {
		return previousChange;
	}

	/**
	 * method to retrieve the current enPassant status of the pawn
	 *
	 * @return true if pawn is currently undergoing en passant, false otherwise
	 */
	public boolean getEnPassant(){
		return enPassant;
	}

	@Override
	public String getPieceName() {
		if (pieceColor==0) {
			this.pieceName="wp";
		}
		else {
			this.pieceName="bp";
		}
		return this.pieceName;
	}

	@Override
	public boolean validMove(Board board, Spot startPosition, Spot endPosition) {
		enPassant = false;

		int yChange=endPosition.getYCoordinate()-startPosition.getYCoordinate();
		int xChange=Math.abs(endPosition.getXCoordinate()-startPosition.getXCoordinate());

		if (board.grid[endPosition.getXCoordinate()][endPosition.getYCoordinate()].isEmpty() && xChange == 1 && ((yChange==1 && color == 1) || (yChange == -1 && color == 0))) { //potential en passant
			if (pieceColor == 0){ //white turn
				if (!board.grid[endPosition.getXCoordinate()][endPosition.getYCoordinate()+1].isEmpty() && board.grid[endPosition.getXCoordinate()][endPosition.getYCoordinate()+1].getPiece().equals("bp") && board.grid[endPosition.getXCoordinate()][endPosition.getYCoordinate()+1].getPiece().previousChange == 2) {
					enPassant = true;
					//System.out.println("enPassant has been set");
					return true;
				}
			}else { //black turn
				if (!board.grid[endPosition.getXCoordinate()][endPosition.getYCoordinate()-1].isEmpty() && board.grid[endPosition.getXCoordinate()][endPosition.getYCoordinate()-1].getPiece().equals("wp") && board.grid[endPosition.getXCoordinate()][endPosition.getYCoordinate()-1].getPiece().previousChange == 2){
					enPassant = true;
					//System.out.println("enPassant has been set");
					return true;
				}
			}
		}

		if ((board.grid[endPosition.getXCoordinate()][endPosition.getYCoordinate()].getPiece()==null && ((yChange==1 && color == 1) || (yChange == -1 && color == 0)) && xChange==0) || (board.grid[endPosition.getXCoordinate()][endPosition.getYCoordinate()].getPiece()!=null && xChange == 1 && ((yChange==1 && color == 1) || (yChange == -1 && color == 0))) || (board.grid[endPosition.getXCoordinate()][endPosition.getYCoordinate()].getPiece()==null && this.first == true && ((yChange == 2 && color == 1) || (yChange == -2 && color == 0))   && xChange == 0)) {
			previousChange = Math.abs(yChange);
			//System.out.println("previous y change was " + previousChange);
			return true;
		}
		return false;
	}
	@Override
	public boolean validMoveWithoutCheck(Board board, Spot startPosition, Spot endPosition) {
		enPassant = false;
		if (!endPosition.isEmpty() && endPosition.getPiece().getColor()==this.getColor()) {  //same color piece
			return false;
		}

		int yChange=endPosition.getYCoordinate()-startPosition.getYCoordinate();
		int xChange=Math.abs(endPosition.getXCoordinate()-startPosition.getXCoordinate());

		if (board.grid[endPosition.getXCoordinate()][endPosition.getYCoordinate()].isEmpty() && xChange == 1 && ((yChange==1 && color == 1) || (yChange == -1 && color == 0))) { //potential en passant
			//System.out.println("looking for enPassant");
			if (pieceColor== 0){ //white turn
				if (!board.grid[endPosition.getXCoordinate()][endPosition.getYCoordinate()+1].isEmpty() && board.grid[endPosition.getXCoordinate()][endPosition.getYCoordinate()+1].getPiece().getPieceName().equals("bp") && board.grid[endPosition.getXCoordinate()][endPosition.getYCoordinate()+1].getPiece().getPreviousChange() == 2) {
					enPassant = true;
					//System.out.println("enPassant has been set");
					return true;
				}
			}else { //black turn
				if (!board.grid[endPosition.getXCoordinate()][endPosition.getYCoordinate()-1].isEmpty() && board.grid[endPosition.getXCoordinate()][endPosition.getYCoordinate()-1].getPiece().getPieceName().equals("wp") && board.grid[endPosition.getXCoordinate()][endPosition.getYCoordinate()-1].getPiece().getPreviousChange() == 2){
					enPassant = true;
					//System.out.println("enPassant has been set");
					return true;
				}
			}
		}
		
		if ((board.grid[endPosition.getXCoordinate()][endPosition.getYCoordinate()].getPiece()==null && ((yChange==1 && color == 1) || (yChange == -1 && color == 0)) && xChange==0) || (board.grid[endPosition.getXCoordinate()][endPosition.getYCoordinate()].getPiece()!=null && xChange == 1 && ((yChange==1 && color == 1) || (yChange == -1 && color == 0))) || (board.grid[endPosition.getXCoordinate()][endPosition.getYCoordinate()].getPiece()==null && this.first == true && ((yChange == 2 && color == 1) || (yChange == -2 && color == 0))   && xChange == 0)) {
			previousChange = Math.abs(yChange);
			//System.out.println("previous y change was " + previousChange);
			return true;
		}
		return false;
	}

}