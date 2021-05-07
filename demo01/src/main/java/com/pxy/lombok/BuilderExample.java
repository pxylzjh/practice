package com.pxy.lombok;

/**
 * @author puxy
 * @version 1.0
 * @description TODO
 * @date 2020/12/28 14:29
 */
public class BuilderExample {
    private Long id;
    private String name;
    private Integer age;

    BuilderExample(final Long id, final String name, final Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public static BuilderExample.BuilderExampleBuilder builder() {
        return new BuilderExample.BuilderExampleBuilder();
    }

    @Override
    public String toString() {
        return "BuilderExample(id=" + this.id + ", name=" + this.name + ", age=" + this.age + ")";
    }

    public static class BuilderExampleBuilder {
        private Long id;
        private String name;
        private Integer age;

        BuilderExampleBuilder() {
        }

        public BuilderExample.BuilderExampleBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        public BuilderExample.BuilderExampleBuilder name(final String name) {
            this.name = name;
            return this;
        }

        public BuilderExample.BuilderExampleBuilder age(final Integer age) {
            this.age = age;
            return this;
        }

        public BuilderExample build() {
            return new BuilderExample(this.id, this.name, this.age);
        }

        @Override
        public String toString() {
            return "BuilderExample.BuilderExampleBuilder(id=" + this.id + ", name=" + this.name + ", age=" + this.age + ")";
        }
    }

    public static void main(String[] args) {
        BuilderExample xy = BuilderExample.builder()
                .age(1).id(2L).name("xy").build();
        System.out.println(xy);
    }
}
