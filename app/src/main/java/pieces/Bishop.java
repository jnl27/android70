package pieces;
import chess.Board;
import chess.Spot;
/**
 * The Bishop class is an extension of the ChessPiece class and creates a Bishop Piece.
 * @author Param Thakker
 * @author Jonathan Lu
 *
 */
public class Bishop extends ChessPiece{
	/**
	 * the pieceName of the Bishop, used to display the text on the ChessBoard in conjunction with color
	 */
	String pieceName="B";
	/**
	 * the Bishop's color (0 white 1 black)
	 */
	int pieceColor=0;
	/**
	 * the amount travelled vertically in the last move of the Bishop
	 */
	int previousChange = 0;

	/**
	 * Constructor with 1 argument
	 *
	 * @param color the color of the Bishop
	 */
	public Bishop(int color) {
		this.setColor(color);
		this.pieceColor=color;
	}
	/**
	 * Constructor with 2 arguments
	 *
	 * @param color the color of the Bishop
	 * @param prevChange the amount travelled vertically in the last move of the Bishop
	 */
	public Bishop(int color, int prevChange) {
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
			this.pieceName="wB";
		}
		else {
			this.pieceName="bB";
		}
		return this.pieceName;
	}

	@Override
	public boolean validMove(Board board, Spot startPosition, Spot endPosition) {
		int yChange=Math.abs(endPosition.getYCoordinate()-startPosition.getYCoordinate());
		int xChange=Math.abs(endPosition.getXCoordinate()-startPosition.getXCoordinate());

		if (yChange==xChange){
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

		if (yChange==xChange){
			previousChange = yChange;
			return true;
		}
		return false;
		}

}