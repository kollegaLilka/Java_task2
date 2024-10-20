package task2.main;

import task2.parser.AbstractFlowersBuilder;
import task2.parser.FlowerBuilderFactory;

public class Main {
    public static void main(String[] args) {
        String type = "stax";
        AbstractFlowersBuilder builder = FlowerBuilderFactory.createStudentBuilder(type);
        builder.buildSetFlowers("flowers.xml");
        System.out.println(builder.getFlowers());
    }
}
