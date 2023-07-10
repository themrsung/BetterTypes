package oasis.bettertypes.string;

import oasis.bettertypes.collection.list.BetterArrayList;
import oasis.bettertypes.collection.list.BetterList;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.awt.*;
import java.util.Objects;
import java.util.function.Function;
import java.util.regex.PatternSyntaxException;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * <h2>Text</h2>
 * <p>
 * The default implementation of {@link BetterString}.
 * This goes against the naming convention of better types due to its frequent usage.
 * Typing in {@code BetterString string = new BetterText("text");} every time would be a nightmare.
 * Therefore, the name was shortened to {@code Text}.
 * </p>
 */
public class Text implements BetterString {
    /**
     * Creates a blank text.
     */
    public Text() {
        this.rawString = "";
        this.color = null;
        this.format = Format.NORMAL;
    }

    /**
     * Creates a new text from a raw {@link String}.
     *
     * @param rawString Raw string
     */
    public Text(@Nonnull String rawString) {
        this.rawString = rawString;
        this.color = null;
        this.format = Format.NORMAL;
    }

    /**
     * Creates a new text with color.
     *
     * @param rawString Raw string
     * @param color     Color
     */
    public Text(@Nonnull String rawString, @Nonnull Color color) {
        this.rawString = rawString;
        this.color = color;
        this.format = Format.NORMAL;
    }

    /**
     * Creates a new text with specified formatting.
     *
     * @param rawString Raw string
     * @param color     Color
     * @param format    Format
     */
    public Text(@Nonnull String rawString, @Nonnull Color color, @Nonnull Format format) {
        this.rawString = rawString;
        this.color = color;
        this.format = format;
    }

    /**
     * Gets a blank builder instance.
     *
     * @return Blank builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Gets a builder instance with default string.
     *
     * @param rawString Raw string
     * @return Builder with given raw string as value
     */
    public static Builder builder(@Nonnull String rawString) {
        return new Builder(rawString);
    }

    /**
     * Gets an unformatted instance of Text from multiple strings.
     *
     * @param rawStrings Strings
     * @return {@link Text}
     */
    public static Text of(@Nonnull String... rawStrings) {
        Text text = new Text();
        for (String s : rawStrings) text = text.append(new Text(s));
        return text;
    }

    /**
     * Gets an unformatted instance of Text from multiple {@link BetterString}s.
     *
     * @param strings Strings
     * @return {@link Text}
     */
    public static Text of(@Nonnull BetterString... strings) {
        Text text = new Text();
        for (BetterString bs : strings) text = text.append(bs);
        return text;
    }

    /**
     * Converts this string to a builder for modification.
     *
     * @return Builder with this string's properties
     */
    public Builder toBuilder() {
        return new Builder(this);
    }

    @Nonnull
    private final String rawString;
    @Nullable
    private final Color color;
    @Nonnull
    private final Format format;

    @Override
    @Nonnull
    public String getRawString() {
        return rawString;
    }

    @Override
    @Nullable
    public Color getColor() {
        return color;
    }

    @Nonnull
    @Override
    public Format getFormat() {
        return format;
    }

    @Nonnull
    @Override
    public Text setRawString(@Nonnull String rawString) {
        return toBuilder().setContent(rawString).build();
    }

    @Nonnull
    @Override
    public Text setColor(@Nullable Color color) {
        return toBuilder().color(color).build();
    }

    @Nonnull
    @Override
    public Text setFormat(@Nonnull Format format) {
        return toBuilder().format(format).build();
    }

    //
    // Builder
    //

    private Text(@Nonnull Builder builder) {
        this.rawString = builder.rawString;
        this.color = builder.color;
        this.format = builder.format;
    }

    public static final class Builder {
        private Builder() {
            this("");
        }

        private Builder(@Nonnull String rawString) {
            this.rawString = rawString;
            this.color = null;
            this.format = Format.NORMAL;
        }

        private Builder(@Nonnull Text text) {
            this.rawString = text.rawString;
            this.color = text.color;
            this.format = text.format;
        }

        @Nonnull
        private String rawString;
        @Nullable
        private Color color;
        @Nonnull
        private Format format;

        @Nonnull
        public Builder setContent(@Nonnull String raw) {
            this.rawString = raw;
            return this;
        }

        @Nonnull
        public Builder append(@Nonnull String raw) {
            this.rawString = this.rawString + raw;
            return this;
        }

        @Nonnull
        public Builder color(@Nullable Color color) {
            this.color = color;
            return this;
        }

        @Nonnull
        public Builder format(@Nonnull Format format) {
            this.format = format;
            return this;
        }

        public Text build() {
            return new Text(this);
        }
    }

    //
    // Implementation
    //


    @Override
    public boolean equals(@Nonnull BetterString other) {
        return rawString.equals(other.getRawString());
    }

    @Override
    public boolean equalsFormat(@Nonnull BetterString other) {
        return Objects.equals(color, other.getColor()) && format == other.getFormat();
    }

    @Override
    public boolean equalsIncludeFormat(@Nonnull BetterString other) {
        return equals(other) && equalsFormat(other);
    }

    @Override
    public boolean equalsIgnoreCase(@Nonnull BetterString other) {
        return rawString.equalsIgnoreCase(other.getRawString());
    }

    @Override
    public boolean equalsIgnoreCaseIncludeFormat(@Nonnull BetterString other) {
        return equalsIgnoreCase(other) && equalsFormat(other);
    }

    @Override
    public boolean startsWith(@Nonnull BetterString other) {
        return rawString.startsWith(other.getRawString());
    }

    @Override
    public boolean startsWithIgnoreCase(@Nonnull BetterString other) {
        return rawString.toLowerCase().startsWith(other.getRawString().toLowerCase());
    }

    @Override
    public boolean contains(@Nonnull BetterString other) {
        return rawString.contains(other.getRawString());
    }

    @Override
    public boolean containsIgnoreCase(@Nonnull BetterString other) {
        return rawString.toLowerCase().contains(other.getRawString().toLowerCase());
    }

    @Nonnull
    @Override
    public Text append(@Nonnull BetterString other) {
        return toBuilder().append(other.getRawString()).build();
    }

    @Nonnull
    @Override
    public BetterString append(@Nonnull BetterList<BetterString> strings) {
        final Builder builder = toBuilder();
        strings.forEach(s -> builder.append(s.getRawString()));
        return builder.build();
    }

    @Override
    public int length() {
        return rawString.length();
    }

    @Override
    public boolean isEmpty() {
        return rawString.isEmpty();
    }

    @Override
    public boolean isBlank() {
        return rawString.isBlank();
    }

    @Override
    public boolean matches(@Nonnull BetterString regex) throws PatternSyntaxException {
        return rawString.matches(regex.getRawString());
    }

    @Nonnull
    @Override
    public Text substring(int beginIndex) throws IndexOutOfBoundsException {
        return toBuilder().setContent(rawString.substring(beginIndex)).build();
    }

    @Nonnull
    @Override
    public Text substring(int beginIndex, int endIndex) throws IndexOutOfBoundsException {
        return toBuilder().setContent(rawString.substring(beginIndex, endIndex)).build();
    }

    @Nonnull
    @Override
    public BetterString concat(@Nonnull String string) {
        return toBuilder().setContent(rawString.concat(string)).build();
    }

    @Nonnull
    @Override
    public BetterString replace(char oldChar, char newChar) {
        return toBuilder().setContent(rawString.replace(oldChar, newChar)).build();
    }

    @Nonnull
    @Override
    public BetterString replace(@Nonnull CharSequence target, @Nonnull CharSequence replacement) {
        return toBuilder().setContent(rawString.replace(target, replacement)).build();
    }

    @Nonnull
    @Override
    public BetterString replaceFirst(@Nonnull BetterString regex, @Nonnull BetterString replacement) throws PatternSyntaxException {
        return toBuilder().setContent(rawString.replaceFirst(regex.getRawString(), replacement.getRawString())).build();
    }

    @Nonnull
    @Override
    public BetterString replaceAll(@Nonnull BetterString regex, @Nonnull BetterString replacement) throws PatternSyntaxException {
        return toBuilder().setContent(rawString.replaceAll(regex.getRawString(), replacement.getRawString())).build();
    }

    @Override
    public char charAt(int index) throws IndexOutOfBoundsException {
        return rawString.charAt(index);
    }

    @Nonnull
    @Override
    public byte[] getBytes() {
        return rawString.getBytes();
    }

    @Nonnull
    @Override
    public BetterList<BetterString> split(@Nonnull BetterString regex) throws PatternSyntaxException {
        String[] split = rawString.split(regex.getRawString());
        BetterList<BetterString> results = new BetterArrayList<>();

        for (String s : split) results.add(toBuilder().setContent(s).build());

        return results;
    }

    @Nonnull
    @Override
    public BetterString toLowerCase() {
        return toBuilder().setContent(rawString.toLowerCase()).build();
    }

    @Nonnull
    @Override
    public BetterString toUpperCase() {
        return toBuilder().setContent(rawString.toUpperCase()).build();
    }

    @Nonnull
    @Override
    public BetterString trim() {
        return toBuilder().setContent(rawString.trim()).build();
    }

    @Nonnull
    @Override
    public BetterString strip() {
        return toBuilder().setContent(rawString.strip()).build();
    }

    @Nonnull
    @Override
    public BetterString stripLeading() {
        return toBuilder().setContent(rawString.stripLeading()).build();
    }

    @Nonnull
    @Override
    public BetterString stripTrailing() {
        return toBuilder().setContent(rawString.stripTrailing()).build();
    }

    @Nonnull
    @Override
    public Stream<BetterString> lines() {
        Stream<String> stream = rawString.lines();
        BetterList<BetterString> betterList = new BetterArrayList<>();

        stream.forEach(s -> betterList.add(toBuilder().setContent(s).build()));

        return betterList.stream();
    }

    @Nonnull
    @Override
    public BetterString indent(int delta) {
        return toBuilder().setContent(rawString.indent(delta)).build();
    }

    @Nonnull
    @Override
    public BetterString stripIndent() {
        return toBuilder().setContent(rawString.stripIndent()).build();
    }

    @Nonnull
    @Override
    public BetterString translateEscapes() throws IllegalArgumentException {
        return toBuilder().setContent(rawString.translateEscapes()).build();
    }

    @Nonnull
    @Override
    public <R> R transform(@Nonnull Function<? super BetterString, ? extends R> function) {
        return function.apply(this);
    }

    @Nonnull
    @Override
    public IntStream chars() {
        return rawString.chars();
    }

    @Nonnull
    @Override
    public char[] toCharArray() {
        return rawString.toCharArray();
    }
}
