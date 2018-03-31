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

package com.fredboat.backend.quarterdeck.rest.v1;

import com.fredboat.backend.quarterdeck.BaseTest;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.Is.isA;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by napster on 28.03.18.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class GuildPlayerControllerTest extends BaseTest {

    @SuppressWarnings("NullableProblems")
    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("NullableProblems")
    @Autowired
    private Gson gson;

    @WithMockUser(roles = "ADMIN")
    @Test
    public void testGet() throws Exception {
        this.mockMvc.perform(get("/v1/guilds/1/player"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.guildId", isA(String.class)))
                .andExpect(jsonPath("$.guildId", is("1")))
                .andExpect(jsonPath("$.voiceChannelId", isA(String.class)))
                .andExpect(jsonPath("$.activeTextChannelId", isA(String.class)))
                .andExpect(jsonPath("$.isPaused", isA(Boolean.class)))
                .andExpect(jsonPath("$.volume", isA(Integer.class)))
                .andExpect(jsonPath("$.repeatMode", isA(String.class)))
                .andExpect(jsonPath("$.isShuffled", isA(Boolean.class)));
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    public void testPatch() throws Exception {
        Map<String, Object> patchGuildPlayer = new HashMap<>();
        patchGuildPlayer.put("voiceChannelId", 42L);
        patchGuildPlayer.put("activeTextChannelId", Long.MAX_VALUE);
        patchGuildPlayer.put("isPaused", false);
        patchGuildPlayer.put("volume", 3);
        patchGuildPlayer.put("repeatMode", "all");

        MockHttpServletRequestBuilder request = patch("/v1/guilds/2/player")
                .content(this.gson.toJson(patchGuildPlayer))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE);

        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.guildId", isA(String.class)))
                .andExpect(jsonPath("$.guildId", is("2")))
                .andExpect(jsonPath("$.voiceChannelId", isA(String.class)))
                .andExpect(jsonPath("$.voiceChannelId", is("42")))
                .andExpect(jsonPath("$.activeTextChannelId", isA(String.class)))
                .andExpect(jsonPath("$.activeTextChannelId", is(Long.toString(Long.MAX_VALUE))))
                .andExpect(jsonPath("$.isPaused", isA(Boolean.class)))
                .andExpect(jsonPath("$.isPaused", is(false)))
                .andExpect(jsonPath("$.volume", isA(Integer.class)))
                .andExpect(jsonPath("$.volume", is(3)))
                .andExpect(jsonPath("$.repeatMode", isA(String.class)))
                .andExpect(jsonPath("$.repeatMode", is("ALL")))
                .andExpect(jsonPath("$.isShuffled", isA(Boolean.class)))
                .andExpect(jsonPath("$.isShuffled", is(false)));
    }
}
