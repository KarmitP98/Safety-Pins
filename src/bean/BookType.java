package bean;

public enum BookType {

    Science, Engineering, Fiction, Maths, Modern, Kids;

    public static BookType getBookType(String type) throws Exception {


        if (type == null)
            return null;

        switch (type.toUpperCase()) {

            case "SCIENCE":
                return BookType.Science;
            case "ENGINEERING":
                return BookType.Engineering;
            case "FINCTION":
                return BookType.Fiction;
            case "MATHS":
                return BookType.Maths;
            case "MODERN":
                return BookType.Modern;
            case "KIDS":
                return BookType.Kids;

            default:
                throw new Exception("Invalid BookType");
        }
    }

}
