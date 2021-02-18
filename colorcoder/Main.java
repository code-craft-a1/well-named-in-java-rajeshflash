package colorcoder;

public class Main {
    enum MajorColor {
        WHITE(0),
        RED(1),
        BLACK(2),
        YELLOW(3),
        VIOLET(4);
        private int index;
        private MajorColor(int index) {
            this.index = index;
        }
        int getIndex() {
            return index;
        }
        public static MajorColor fromIndex(int index) {
            for(MajorColor color: MajorColor.values()) {
                if(color.getIndex() == index) {
                    return color;
                }
            }
            return null;
        }
    };
    enum MinorColor {
        BLUE(0),
        ORANGE(1),
        GREEN(2),
        BROWN(3),
        SLATE(4);
        private int index;
        private MinorColor(int index) {
            this.index = index;
        }
        int getIndex() {
            return index;
        }
        public static MinorColor fromIndex(int index) {
            for(MinorColor color: MinorColor.values()) {
                if(color.getIndex() == index) {
                    return color;
                }
            }
            return null;
        }
    };

    final static String MajorColorNames[] = {
        "White", "Red", "Black", "Yellow", "Violet"
    };
    final static int numberOfMajorColors = MajorColorNames.length;
    final static String MinorColorNames[] = {
        "Blue", "Orange", "Green", "Brown", "Slate"
    };
    final static int numberOfMinorColors = MinorColorNames.length;

    static class ColorPair {
        private MajorColor majorColor;
        private MinorColor minorColor;
        
        public ColorPair(MajorColor major, MinorColor minor)
        {
            majorColor = major;
            minorColor = minor;
        }
        public MajorColor getMajor() {
            return majorColor;
        }
        public MinorColor getMinor() {
            return minorColor;
        }
        String ToString() {
            String colorPairStr = MajorColorNames[majorColor.getIndex()];
            colorPairStr += " ";
            colorPairStr += MinorColorNames[minorColor.getIndex()];
            return colorPairStr;
        }
    };

    static ColorPair GetColorFromPairNumber(int pairNumber) {
        int zeroBasedPairNumber = pairNumber - 1;
        MajorColor majorColor = 
            MajorColor.fromIndex(zeroBasedPairNumber / numberOfMinorColors);
        MinorColor minorColor =
            MinorColor.fromIndex(zeroBasedPairNumber % numberOfMinorColors);
        return new ColorPair(majorColor, minorColor);
    }
    static int GetPairNumberFromColor(MajorColor major, MinorColor minor) {
        return major.getIndex() * numberOfMinorColors + minor.getIndex() + 1;
    }

    static void testNumberToPair(int pairNumber,
        MajorColor expectedMajor,
        MinorColor expectedMinor)
    {
        ColorPair colorPair = GetColorFromPairNumber(pairNumber);
        System.out.println("Got pair " + colorPair.ToString());
        assert(colorPair.getMajor() == expectedMajor);
        assert(colorPair.getMinor() == expectedMinor);
    }

    static void testPairToNumber(
        MajorColor major,
        MinorColor minor,
        int expectedPairNumber)
    {
        int pairNumber = GetPairNumberFromColor(major, minor);
        System.out.println("Got pair number " + pairNumber);
        assert(pairNumber == expectedPairNumber);
    }

    public static void main(String[] args) {
        testNumberToPair(4, MajorColor.WHITE, MinorColor.BROWN);
        testNumberToPair(5, MajorColor.WHITE, MinorColor.SLATE);
    
        testPairToNumber(MajorColor.BLACK, MinorColor.ORANGE, 12);
        testPairToNumber(MajorColor.VIOLET, MinorColor.SLATE, 25);
    }
}
