package esi.atlg3.g51999.othello.view.graphics.composants;

import esi.atlg3.g51999.othello.model.Piece;
import esi.atlg3.g51999.othello.model.PlayerColor;
import esi.atlg3.g51999.othello.utils.Configs;
import javafx.animation.Animation.Status;
import javafx.animation.RotateTransition;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.Cylinder;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

/**
 * A Piece will be displayed as a cylinder with one side White an another side
 * Black. To change the piece color, a rotation will be used.
 *
 * @author Andre Pereira
 */
public class FxPiece extends Cylinder {

    private final RotateTransition rt;

    /**
     * Creates a Piece who fits in a square.
     */
    public FxPiece() {
        super();
        super.setRadius(Configs.SQUARE_SIZE / 2 - 12);
        super.setHeight(super.getRadius() / 4);
        this.rt = new RotateTransition(Duration.millis(800), this);
    }

    /**
     * Initializes the Piece with the default Color Transparent.
     */
    public void initFxPiece() {
        PhongMaterial pieceHider = new PhongMaterial();
        pieceHider.setDiffuseColor(Color.TRANSPARENT);
        super.setMaterial(pieceHider);
        super.setRotationAxis(Rotate.X_AXIS);
        super.setRotate(90);
        //Sets one side black.
        super.setCullFace(CullFace.NONE);

        this.rt.setByAngle(180);
        this.rt.setCycleCount(1);
        this.rt.setAutoReverse(false);
    }

    /**
     * Rotates the piece changing the displayed color. If a rotation already
     * running, it will rotate to the new color at the end of the rotation to
     * not interrupt the old rotation and changing the angles.
     */
    public void rotatePiece() {
        if (rt.getStatus() == Status.RUNNING) {
            rt.setOnFinished((event) -> {
                rt.play();
                rt.setOnFinished((nEvent) -> {
                });
            });
        } else {
            rt.play();
        }

    }

    /**
     * Draws the Piece if it's not displayed.
     *
     * @param dataPiece The piece to be draw.
     */
    public void drawPiece(Piece dataPiece) {
        PhongMaterial pieceBackground = new PhongMaterial();
        pieceBackground.setDiffuseColor(Color.WHITE);
        super.setMaterial(pieceBackground);
        if (dataPiece.getColor() == PlayerColor.BLACK) {
           super.setRotate(270);
        } else {
           super.setRotate(90);
        }

    }

}
