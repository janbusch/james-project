/****************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one   *
 * or more contributor license agreements.  See the NOTICE file *
 * distributed with this work for additional information        *
 * regarding copyright ownership.  The ASF licenses this file   *
 * to you under the Apache License, Version 2.0 (the            *
 * "License"); you may not use this file except in compliance   *
 * with the License.  You may obtain a copy of the License at   *
 *                                                              *
 *   http://www.apache.org/licenses/LICENSE-2.0                 *
 *                                                              *
 * Unless required by applicable law or agreed to in writing,   *
 * software distributed under the License is distributed on an  *
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY       *
 * KIND, either express or implied.  See the License for the    *
 * specific language governing permissions and limitations      *
 * under the License.                                           *
 ****************************************************************/

package org.apache.james.imap.encode;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.james.imap.encode.base.ByteImapResponseWriter;
import org.apache.james.imap.encode.base.ImapResponseComposerImpl;
import org.apache.james.imap.message.response.ListResponse;
import org.apache.james.mailbox.model.MailboxMetaData;
import org.junit.Before;
import org.junit.Test;

public class ListResponseEncoderTest {
    private ListResponseEncoder encoder;
    private ByteImapResponseWriter writer = new ByteImapResponseWriter();
    private ImapResponseComposer composer = new ImapResponseComposerImpl(writer);

    @Before
    public void setUp() throws Exception {
        encoder = new ListResponseEncoder();
    }

    @Test
    public void encoderShouldAcceptListResponse() {
        assertThat(encoder.acceptableMessages()).isEqualTo(ListResponse.class);
    }

    @Test
    public void encoderShouldIncludeListCommand() throws Exception {
        encoder.encode(new ListResponse(MailboxMetaData.Children.HAS_CHILDREN, MailboxMetaData.Selectability.NONE, "name", '.'), composer);
        assertThat(writer.getString()).startsWith("* LIST");
    }
}
