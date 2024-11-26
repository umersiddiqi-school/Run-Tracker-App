package org.example.capstone.model;

public class BMI {

    public class Bmi {
        private int id;
        private int userId;
        private double height;
        private double weight;
        private double bmiValue;

        // Constructors
        public Bmi() { }

        public Bmi(int userId, double height, double weight, double bmiValue) {
            this.userId = userId;
            this.height = height;
            this.weight = weight;
            this.bmiValue = bmiValue;
        }

        public Bmi(int id, int userId, double height, double weight, double bmiValue) {
            this.id = id;
            this.userId = userId;
            this.height = height;
            this.weight = weight;
            this.bmiValue = bmiValue;
        }

        // Getters and Setters
        public int getId() { return id; }
        public void setId(int id) { this.id = id; }

        public int getUserId() { return userId; }
        public void setUserId(int userId) { this.userId = userId; }

        public double getHeight() { return height; }
        public void setHeight(double height) { this.height = height; }

        public double getWeight() { return weight; }
        public void setWeight(double weight) { this.weight = weight; }

        public double getBmiValue() { return bmiValue; }
        public void setBmiValue(double bmiValue) { this.bmiValue = bmiValue; }

        @Override
        public String toString() {
            return "Bmi [id=" + id + ", userId=" + userId + ", height=" + height + ", weight=" + weight + ", bmiValue=" + bmiValue + "]";
        }
    }

}
