package com.roma.berendeev.yandex;

import android.support.annotation.Nullable;

import org.junit.Test;

class JavaRectangles {

    public static void test_intersect() {
        Rectangle first = new Rectangle(2, 2, 3, 3);
        Rectangle second1 = new Rectangle(1, 1, 3, 2);
        Rectangle second2 = new Rectangle(1, 4, 2, 3);
        Rectangle second3 = new Rectangle(4, 4, 3, 3);
        Rectangle second4 = new Rectangle(4, 1, 3, 2);
        Rectangle second5 = new Rectangle(5, 5, 3, 2);
        Rectangle second6 = new Rectangle(3, 3, 1, 1);
        System.out.println(intersect(first, second1).toString());
        System.out.println(intersect(first, second2).toString());
        System.out.println(intersect(first, second3).toString());
        System.out.println(intersect(first, second4).toString());
        System.out.println(intersect(first, second6).toString());
        System.out.println(intersect(first, second5) == null);
    }

    @Nullable
    public static Rectangle intersect(Rectangle first, Rectangle second) {
        int x1 = Math.max(first.x, second.x);
        int x2 = Math.min(first.x + first.width, second.x + second.width);
        if (x2 <= x1) return null;
        int y1 = Math.max(first.y, second.y);
        int y2 = Math.min(first.y + first.height, second.y + second.height);
        if (y2 <= y1) return null;
        return new Rectangle(x1, y1, x2 - x1, y2 - y1);
    }

    public static class Rectangle {
        int x, y, width, height;

        Rectangle(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        @Override
        public String toString() {
            return "x = " + x + ", y = " + y + ", width = " + width + ", height = " + height;
        }
    }
}
