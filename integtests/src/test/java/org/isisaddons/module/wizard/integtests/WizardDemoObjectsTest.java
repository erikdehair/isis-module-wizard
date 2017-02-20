/*
 *  Copyright 2014~2015 Dan Haywood
 *
 *  Licensed under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.isisaddons.module.wizard.integtests;

import org.apache.isis.applib.fixturescripts.FixtureScripts;
import org.assertj.core.api.Assertions;
import org.isisaddons.module.wizard.fixture.dom.WizardDemoObject;
import org.isisaddons.module.wizard.fixture.dom.WizardDemoObjects;
import org.isisaddons.module.wizard.fixture.scripts.scenarios.WizardDemoObjectsFixture;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;
import java.util.List;


public class WizardDemoObjectsTest extends WizardModuleIntegTest {

    @Inject
    FixtureScripts fixtureScripts;

    @Inject
    private WizardDemoObjects wizardDemoObjects;

    @Before
    public void setUpData() throws Exception {
        fixtureScripts.runFixtureScript(new WizardDemoObjectsFixture(), null);
    }


    @Test
    public void listAll() throws Exception {

        final List<WizardDemoObject> all = wrap(wizardDemoObjects).listAll();
        Assertions.assertThat(all.size()).isEqualTo(3);
        
        WizardDemoObject wizardDemoObject = wrap(all.get(0));
        Assertions.assertThat(wizardDemoObject.getName()).isEqualTo("Foo");
    }
    
    @Test
    public void create() throws Exception {

        wrap(wizardDemoObjects).create("Faz");
        
        final List<WizardDemoObject> all = wrap(wizardDemoObjects).listAll();
        Assertions.assertThat(all.size()).isEqualTo(4);
    }

}