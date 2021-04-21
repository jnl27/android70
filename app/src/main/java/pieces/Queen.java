package pieces;
import chess.Board;
import chess.Spot;
/**
 * The Queen class is an extension of the ChessPiece class and creates a Queen Piece.
 * @author Param Thakker
 * @author Jonathan Lu
 *
 */
public class Queen extends ChessPiece{
	/**
	 * the pieceName of the Queen, used to display the text on the ChessBoard in conjunction with color
	 */
	String pieceName="Q";
	/**
	 * the color of the Queen (0 white 1 black)
	 */
	int pieceColor=0;
	/**
	 * the amount travelled vertically in the last move of the Queen
	 */
	int previousChange = 0;

	/**
	 * Constructor with 1 argument
	 *
	 * @param color the color of the Queen
	 */
	public Queen(int color) {
		this.setColor(color);
		this.pieceColor=color;
	}

	/**
	 * Constructor with 2 arguments
	 *
	 * @param color the color of the Queen
	 * @param prevChange the amount travelled vertically in the last move of the Queen
	 */
	public Queen(int color, int prevChange) {
		this.setColor(color);
		this.pieceColor=color;
		this.previousChange = prevChange;
	}
	public int getPreviousChange() {
		return previousChange;
	}
	@Override
	public String getPieceName() {
		
		if (pieceColor==0) {
			this.pieceName="wQ";
		}
		else {
			this.pieceName="bQ";
		}
		return this.pieceName;
	}

	@Override
	public boolean validMove(Board board, Spot startPosition, Spot endPosition) {
		int yChange=Math.abs(endPosition.getYCoordinate()-startPosition.getYCoordinate());
		int xChange=Math.abs(endPosition.getXCoordinate()-startPosition.getXCoordinate());

		if ((xChange==yChange) || (xChange == 0 && yChange != 0) || (xChange != 0 && yChange == 0)){
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
		int yChange=Math.abs(endPosition.getYCoordinate()-startPosition.getYCoordinate());
		int xChange=Math.abs(endPosition.getXCoordinate()-startPosition.getXCoordinate());

		if ((xChange==yChange) || (xChange == 0 && yChange != 0) || (xChange != 0 && yChange == 0)){
			previousChange = yChange;
			return true;
		}
		return false;
		
	}

}