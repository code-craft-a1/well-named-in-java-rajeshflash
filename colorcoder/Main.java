package colorcoder;

public final class Main {
    enum MajorColor {
        WHITE(0),
        RED(1),
        BLACK(2),
        YELLOW(3),
        VIOLET(4);
        private int index;
        MajorColor(int index) {
            this.index = index;
        }
        int getColorIndex() {
            return index;
        }
        public static MajorColor fromColorIndex(int index) {
            for(MajorColor color: MajorColor.values()) {
                if(color.getColorIndex() == index) {
                    return color;
                }
            }
            return null;
        }
    }
    
    enum MinorColor {
        BLUE(0),
        ORANGE(1),
        GREEN(2),
        BROWN(3),
        SLATE(4);
        private int index;
        MinorColor(int index) {
            this.index = index;
        }
        int getColorIndex() {
            return index;
        }
        public static MinorColor fromColorIndex(int index) {
            for(MinorColor color: MinorColor.values()) {
                if(color.getColorIndex() == index) {
                    return color;
                }
            }
            return null;
        }
    }

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
        public MajorColor getMajorColor() {
            return majorColor;
        }
        public MinorColor getMinorColor() {
            return minorColor;
        }
        String getColorPairName() {
            String colorPairStr = MajorColorNames[majorColor.getColorIndex()];
            colorPairStr += " ";
            colorPairStr += MinorColorNames[minorColor.getColorIndex()];
            return colorPairStr;
        }
    }

    static ColorPair getColorFromPairNumber(int pairNumber) {
        int zeroBasedPairNumber = pairNumber - 1;
        MajorColor majorColor = 
            MajorColor.fromColorIndex(zeroBasedPairNumber / numberOfMinorColors);
        MinorColor minorColor =
            MinorColor.fromColorIndex(zeroBasedPairNumber % numberOfMinorColors);
        return new ColorPair(majorColor, minorColor);
    }
    static int getPairNumberFromColor(MajorColor major, MinorColor minor) {
        return major.getColorIndex() * numberOfMinorColors + minor.getColorIndex() + 1;
    }

    static void testNumberToPair(int pairNumber,
        MajorColor expectedMajor,
        MinorColor expectedMinor)
    {
        ColorPair colorPair = getColorFromPairNumber(pairNumber);
        System.out.println("Got pair " + colorPair.getColorPairName());
        assert colorPair.getMajorColor() == expectedMajor;
        assert colorPair.getMinorColor() == expectedMinor;
    }

    static void testPairToNumber(
        MajorColor major,
        MinorColor minor,
        int expectedPairNumber)
    {
        int pairNumber = getPairNumberFromColor(major, minor);
        System.out.println("Got pair number " + pairNumber);
        assert pairNumber == expectedPairNumber;
    }

    public static void main(String[] args) {
        testNumberToPair(4, MajorColor.WHITE, MinorColor.BROWN);
        testNumberToPair(5, MajorColor.WHITE, MinorColor.SLATE);
    
        testPairToNumber(MajorColor.BLACK, MinorColor.ORANGE, 12);
        testPairToNumber(MajorColor.VIOLET, MinorColor.SLATE, 25);
    }
}
