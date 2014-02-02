package com.jguice.quizzle.types;

/**
 * $Log: not supported by cvs2svn $
 *
 * @author $Author: josh $
 * @version $Version$
 */
public class QuizType {

    private String name;
    private String extension;

    public QuizType(String name, String extension) {
        this.name = name;
        this.extension = extension;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String toString() {
        return this.name;
    }
}
