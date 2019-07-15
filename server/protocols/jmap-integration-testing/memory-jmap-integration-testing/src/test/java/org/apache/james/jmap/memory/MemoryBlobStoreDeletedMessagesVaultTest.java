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

package org.apache.james.jmap.memory;

import java.io.IOException;
import java.time.Clock;

import org.apache.james.GuiceJamesServer;
import org.apache.james.MemoryJmapTestRule;
import org.apache.james.filesystem.api.FileSystem;
import org.apache.james.jmap.methods.integration.DeletedMessagesVaultTest;
import org.apache.james.modules.mailbox.MemoryDeletedMessageVaultModule;
import org.apache.james.modules.vault.TestDeleteMessageVaultPreDeletionHookModule;
import org.apache.james.webadmin.WebAdminConfiguration;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

public class MemoryBlobStoreDeletedMessagesVaultTest extends DeletedMessagesVaultTest {
    @Rule
    public MemoryJmapTestRule memoryJmap = new MemoryJmapTestRule();

    @Override
    protected GuiceJamesServer createJmapServer(FileSystem fileSystem, Clock clock) throws IOException {
        return memoryJmap.jmapServer(
            new MemoryDeletedMessageVaultModule(),
            new TestDeleteMessageVaultPreDeletionHookModule(),
            binder -> binder.bind(WebAdminConfiguration.class).toInstance(WebAdminConfiguration.TEST_CONFIGURATION),
            binder -> binder.bind(FileSystem.class).toInstance(fileSystem),
            binder -> binder.bind(Clock.class).toInstance(clock));
    }

    @Override
    protected void awaitSearchUpToDate() {

    }

    @Ignore("Will be implemented latter")
    @Test
    public void vaultDeleteShouldDeleteMessageThenExportWithNoEntry() throws Exception {
    }

    @Ignore("Will be implemented latter")
    @Test
    public void vaultDeleteShouldNotDeleteEmptyVaultThenExportNoEntry() throws Exception {
    }

    @Ignore("Will be implemented latter")
    @Test
    public void vaultDeleteShouldNotDeleteNotMatchedMessageInVaultThenExportAnEntry() throws Exception {
    }

    @Ignore("Will be implemented latter")
    @Test
    public void vaultDeleteShouldNotAppendMessageToTheUserMailbox() {
    }
}