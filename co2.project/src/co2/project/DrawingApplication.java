package co2.project;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class DrawingApplication {

    static abstract class Shape {
        protected int x, y; 

        public Shape(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public abstract void draw();

        public abstract void resize(int factor);
    }

    static class Circle extends Shape {
        private int radius;

        public Circle(int x, int y, int radius) {
            super(x, y);
            this.radius = radius;
        }

        @Override
        public void draw() {
            System.out.println("Drawing Circle at (" + x + ", " + y + ") with radius " + radius);
        }

        @Override
        public void resize(int factor) {
            this.radius *= factor;
            System.out.println("Resized Circle to radius " + radius);
        }
    }

    static class Rectangle extends Shape {
        private int width, height;

        public Rectangle(int x, int y, int width, int height) {
            super(x, y);
            this.width = width;
            this.height = height;
        }

        @Override
        public void draw() {
            System.out.println("Drawing Rectangle at (" + x + ", " + y + ") with width " + width + " and height " + height);
        }

        @Override
        public void resize(int factor) {
            this.width *= factor;
            this.height *= factor;
            System.out.println("Resized Rectangle to width " + width + " and height " + height);
        }
    }

    private class Canvas {
        private List<Shape> shapes = new ArrayList<>();

        public void addShape(Shape shape) {
            shapes.add(shape);
            shape.draw();
        }

        public void clearCanvas() {
            shapes.clear();
            System.out.println("Canvas cleared");
        }

        // Apply an operation to all shapes (resize, move, etc.) using a lambda expression
        public void applyToShapes(Consumer<Shape> operation) {
            shapes.forEach(operation);
        }
    }

  
    public void start() {
        Canvas canvas = new Canvas();

        canvas.addShape(new Circle(10, 20, 15));
        canvas.addShape(new Rectangle(30, 40, 50, 60));

        System.out.println("\nResizing all shapes by a factor of 2:");
        canvas.applyToShapes(shape -> shape.resize(2));
        canvas.clearCanvas();
    }

    public static void main(String[] args) {
        DrawingApplication app = new DrawingApplication();
        app.start();
    }
}
