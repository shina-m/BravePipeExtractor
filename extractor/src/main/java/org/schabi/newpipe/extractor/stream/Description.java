/*
 * SPDX-FileCopyrightText: 2026 NewPipe e.V. <https://newpipe-ev.de>
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package org.schabi.newpipe.extractor.stream;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.Serializable;

import static org.schabi.newpipe.extractor.utils.Utils.isNullOrEmpty;

public record Description(@Nonnull String content, @Nonnull Type type) implements Serializable {
    @Nonnull
    public static final Description EMPTY_DESCRIPTION = new Description("", Type.PLAIN_TEXT);

    // BravePipe: keep the pre-0.27 integer constants and bean-style accessors so that the
    // BravePipe app keeps compiling against both the published 0.26.x artifact and this fork.
    public static final int HTML = 0;
    public static final int MARKDOWN = 1;
    public static final int PLAIN_TEXT = 2;

    public enum Type {
        HTML, MARKDOWN, PLAIN_TEXT
    }

    public Description(@Nullable final String content, final int type) {
        this(content, Type.values()[type]);
    }

    @Nonnull
    public String getContent() {
        return content;
    }

    public int getType() {
        return type.ordinal();
    }

    @Nonnull
    public static Description of(@Nullable final String content, @Nonnull final Type type) {
        if (isNullOrEmpty(content)) {
            return EMPTY_DESCRIPTION;
        }
        return new Description(content, type);
    }
}
