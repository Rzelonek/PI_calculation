package rzelonek.pi;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.canvas.*;

public class PrimaryController {

    @FXML
    private Label resultLabel;


    @FXML
    private void calculatePi() {
        int totalPoints = 1000; // You can adjust the total number of points here
        double pi = MonteCarloPiCalculator.calculatePi(totalPoints);
        resultLabel.setText(String.format("π ≈ %.6f", pi));
     
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private Canvas drawArea;
    private GraphicsContext gc;

    @FXML
    private Canvas canvas;

    @FXML
    private void initialize() {
        drawAxes();
    }

    private void drawAxes() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        double width = canvas.getWidth();
        double height = canvas.getHeight();

        // Clear canvas
        gc.clearRect(0, 0, width, height);

        // Set line color
        gc.setStroke(Color.BLACK);

        // Draw X-axis
        gc.strokeLine(0, height / 2, width, height / 2);

        // Draw Y-axis
        gc.strokeLine(width / 2, 0, width / 2, height);

        // Draw ticks on X-axis
        for (double x = -2; x <= 2; x += 0.5) {
            double xPos = mapXCoordinate(x, width);
            gc.strokeLine(xPos, height / 2 - 5, xPos, height / 2 + 5);
        }

        // Draw ticks on Y-axis
        for (double y = -2; y <= 2; y += 0.5) {
            double yPos = mapYCoordinate(y, height);
            gc.strokeLine(width / 2 - 5, yPos, width / 2 + 5, yPos);
        }

        // Draw circle with radius 1 in the middle
        double circleCenterX = width / 2;
        double circleCenterY = height / 2;
        double circleRadius = mapXCoordinate(1, width) - circleCenterX;
        gc.strokeOval(circleCenterX - circleRadius, circleCenterY - circleRadius, 2 * circleRadius, 2 * circleRadius);

         // Draw square with side length 1
        double squareSide = mapXCoordinate(2, width) - mapXCoordinate(0, width); // Side length of square
        double squareX = circleCenterX - squareSide / 2;
        double squareY = circleCenterY - squareSide / 2;
        gc.strokeRect(squareX, squareY, squareSide, squareSide);

    }

    // Map X-coordinate from [-2, 2] to canvas width
    private double mapXCoordinate(double x, double canvasWidth) {
        return ((x + 2) / 4) * canvasWidth;
    }

    // Map Y-coordinate from [-2, 2] to canvas height
    private double mapYCoordinate(double y, double canvasHeight) {
        return ((2 - y) / 4) * canvasHeight;
    }

}



