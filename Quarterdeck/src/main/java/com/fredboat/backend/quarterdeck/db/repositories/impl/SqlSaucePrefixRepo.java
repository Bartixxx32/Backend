/*
 * MIT License
 *
 * Copyright (c) 2016-2018 The FredBoat Org https://github.com/FredBoat/
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package com.fredboat.backend.quarterdeck.db.repositories.impl;

import com.fredboat.backend.quarterdeck.db.entities.main.GuildBotId;
import com.fredboat.backend.quarterdeck.db.entities.main.Prefix;
import com.fredboat.backend.quarterdeck.db.repositories.api.PrefixRepo;
import org.springframework.stereotype.Component;
import space.npstr.sqlsauce.DatabaseWrapper;

import java.util.Collection;

/**
 * Created by napster on 05.02.18.
 */
@Component
public class SqlSaucePrefixRepo extends SqlSauceRepo<GuildBotId, Prefix> implements PrefixRepo {

    public SqlSaucePrefixRepo(DatabaseWrapper dbWrapper) {
        super(dbWrapper, Prefix.class);
    }

    @Override
    public Prefix addPrefixes(GuildBotId id, Collection<String> prefixes) {
        return this.dbWrapper.findApplyAndMerge(Prefix.key(id), prefix -> prefix.addPrefixes(prefixes));
    }

    @Override
    public Prefix removePrefixes(GuildBotId id, Collection<String> prefixes) {
        return this.dbWrapper.findApplyAndMerge(Prefix.key(id), prefix -> prefix.removePrefixes(prefixes));
    }
}
