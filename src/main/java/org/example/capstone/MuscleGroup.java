package org.example.capstone;

public class MuscleGroup {

        private String name;
        private double size;
        private double strength;

        public MuscleGroup(String name) {
            this.name = name;
            this.size = 0.0;
            this.strength = 0.0;
        }

        public String getName() {
            return name;
        }

        public double getSize() {
            return size;
        }

        public void setSize(double size) {
            this.size = size;
        }

        public double getStrength() {
            return strength;
        }

        public void setStrength(double strength) {
            this.strength = strength;
        }
    }



