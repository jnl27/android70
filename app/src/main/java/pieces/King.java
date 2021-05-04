package pieces;
import android.util.Log;

import chess.Board;
import chess.Spot;

/**
 * The King class is an extension of the ChessPiece class and creates a King Piece.
 * @author Param Thakker
 * @author Jonathan Lu
 *
 */
public class King extends ChessPiece{
	/**
	 * "K" the name used to display a King on the ChessBoard in conjunction with color
	 */
	String pieceName="K";
	/**
	 * boolean castledQ to display if a King has been castled on Queens side
	 */
	public static boolean castledQ = false;
	/**
	 * boolean castledK to display if a King has been castled on Kings side
	 */
	public static boolean castledK = false;
	/**
	 * the King's color (0 white, 1 black)
	 */
	int pieceColor=0;
	/**
	 * the amount travelled vertically in the last move of the King
	 */
	int previousChange=0;
	/**
	 * Constructor with 2 arguments
	 *
	 * @param color the color of the King
	 */
	public King(int color) {
		this.setColor(color);
		this.pieceColor=color;
	}
	/**
	 * Constructor with 2 arguments
	 *
	 * @param color the color of the King
	 * @param previousChange the amount travelled vertically in the last move of the King
	 */
	public King(int color, int previousChange) {
		this.setColor(color);
		this.pieceColor=color;
		this.previousChange = previousChange;
	}

	public int getPreviousChange() {
		return previousChange;
	}

	@Override
	public String getPieceName() {
		
		if (pieceColor==0) {
			this.pieceName="wK";
		}
		else {
			this.pieceName="bK";
		}
		return this.pieceName;
	}

	@Override
	public boolean validMove(Board board, Spot startPosition, Spot endPosition) {
		//white castling
		if (color == 0){
			//kings side
			if (startPosition.getXCoordinate() == 4  && startPosition.getYCoordinate() == 7 && endPosition.getXCoordinate() == 6 && endPosition.getYCoordinate() == 7 && first && !board.grid[7][7].isEmpty() && board.grid[7][7].getPiece().getPieceName().equals("wR") && board.grid[7][7].getPiece().first && board.grid[5][7].isEmpty() && board.grid[6][7].isEmpty()){
				Log.d("ChessApp","**white castle kings side**");
				castledK = true;
				first = false;
				return true;
			}
			//queens side
			if (startPosition.getXCoordinate() == 4 && startPosition.getYCoordinate() == 7 && endPosition.getXCoordinate() == 2 && endPosition.getYCoordinate() == 7 && first && !board.grid[0][7].isEmpty() && board.grid[0][7].getPiece().getPieceName().equals("wR") && board.grid[0][7].getPiece().first && board.grid[1][7].isEmpty() && board.grid[2][7].isEmpty() && board.grid[3][7].isEmpty()){
				Log.d("ChessApp","**white castle queens side**");
				castledQ = true;
				first = false;
				return true;
			}
		}
		//black castling
		if (color == 1) {
			//kings side
			if (startPosition.getXCoordinate() == 4 && startPosition.getYCoordinate() == 0 && endPosition.getXCoordinate() == 6 && endPosition.getYCoordinate() == 0 && first && !board.grid[7][0].isEmpty() && board.grid[7][0].getPiece().getPieceName().equals("bR") && board.grid[7][0].getPiece().first && board.grid[5][0].isEmpty() && board.grid[6][0].isEmpty()){
				Log.d("ChessApp","**black castle kings side**");
				castledK = true;
				first = false;
				return true;
			}
			//queens side
			if (startPosition.getXCoordinate() == 4 && startPosition.getYCoordinate() == 0 && endPosition.getXCoordinate() == 2 && endPosition.getYCoordinate() == 0 && first && !board.grid[0][0].isEmpty() && board.grid[0][0].getPiece().getPieceName().equals("bR") && board.grid[0][0].getPiece().first && board.grid[1][0].isEmpty() && board.grid[2][0].isEmpty() && board.grid[3][0].isEmpty()){
				Log.d("ChessApp","**black castle queens side**");
				castledQ = true;
				first = false;
				return true;
			}

		}
		int yChange=Math.abs(endPosition.getYCoordinate()-startPosition.getYCoordinate());
		int xChange=Math.abs(endPosition.getXCoordinate()-startPosition.getXCoordinate());

		if ((xChange == 1 && yChange ==1) || (xChange ==1 && yChange == 0) || (xChange ==0 && yChange == 1)){
			previousChange = yChange;
			return true;
		}
		return false;
		
	}


	@Override
	public boolean validMoveWithoutCheck(Board board, Spot startPosition, Spot endPosition) {
		if (!endPosition.isEmpty() && endPosition.getPiece().getColor()==this.getColor()) {
			return false;
		}
		//white castling
		if (color == 0){
			//kings side
			if (startPosition.getXCoordinate() == 4  && startPosition.getYCoordinate() == 7 && endPosition.getXCoordinate() == 6 && endPosition.getYCoordinate() == 7 && first && board.grid[7][7].getPiece().getPieceName().equals("wR") && board.grid[7][7].getPiece().first && board.grid[5][7].isEmpty() && board.grid[6][7].isEmpty()){
				Log.d("ChessApp","**white castle kings side**");
				castledK = true;
				first = false;
				return true;
			}
			//queens side
			if (startPosition.getXCoordinate() == 4 && startPosition.getYCoordinate() == 7 && endPosition.getXCoordinate() == 2 && endPosition.getYCoordinate() == 7 && first && board.grid[0][7].getPiece().getPieceName().equals("wR") && board.grid[0][7].getPiece().first && board.grid[1][7].isEmpty() && board.grid[2][7].isEmpty() && board.grid[3][7].isEmpty()){
				Log.d("ChessApp","**white castle queens side**");
				castledQ = true;
				first = false;
				return true;
			}
		}
		//black castling
		if (color == 1){
			//kings side
			if (startPosition.getXCoordinate() == 4 && startPosition.getYCoordinate() == 0 && endPosition.getXCoordinate() == 6 && endPosition.getYCoordinate() == 0 && first && board.grid[7][0].getPiece().getPieceName().equals("bR") && board.grid[7][0].getPiece().first && board.grid[5][0].isEmpty() && board.grid[6][0].isEmpty()){
				Log.d("ChessApp","**black castle kings side**");
				castledK = true;
				first = false;
				return true;
			}
			//queens side
			if (startPosition.getXCoordinate() == 4 && startPosition.getYCoordinate() == 0 && endPosition.getXCoordinate() == 2 && endPosition.getYCoordinate() == 0 && first && board.grid[0][0].getPiece().getPieceName().equals("bR") && board.grid[0][0].getPiece().first && board.grid[1][0].isEmpty() && board.grid[2][0].isEmpty() && board.grid[3][0].isEmpty()){
				Log.d("ChessApp", "**black castle queens side**");
				castledQ = true;
				first = false;
				return true;
			}
		}
		int yChange=Math.abs(endPosition.getYCoordinate()-startPosition.getYCoordinate());
		int xChange=Math.abs(endPosition.getXCoordinate()-startPosition.getXCoordinate());

		if ((xChange == 1 && yChange ==1) || (xChange ==1 && yChange == 0) || (xChange ==0 && yChange == 1)){
			previousChange = yChange;
			return true;
		}
		return false;
	}

}