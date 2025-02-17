// Copyright 2021-present StarRocks, Inc. All rights reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.


package com.starrocks.sql.ast;

import java.util.Collections;
import java.util.List;

public class GrantPrivilegeStmt extends BaseGrantRevokePrivilegeStmt {
    public GrantPrivilegeStmt(
            List<String> privilegeTypeUnResolved,
            String objectTypeUnResolved,
            GrantRevokeClause grantRevokeClause,
            GrantRevokePrivilegeObjects objects) {
        super(privilegeTypeUnResolved, objectTypeUnResolved, grantRevokeClause, objects);
    }

    /**
     * The following 2 functions is used to generate sql when excuting `show grants` in old privilege framework
     */
    public GrantPrivilegeStmt(List<String> privilegeTypeUnResolved, String objectTypeUnResolved, UserIdentity userIdentity) {
        super(privilegeTypeUnResolved, objectTypeUnResolved, new GrantRevokeClause(userIdentity, null, false),
                new GrantRevokePrivilegeObjects());
    }

    public GrantPrivilegeStmt(List<String> privilegeTypeUnResolved, String objectTypeUnResolved, String roleName) {
        super(privilegeTypeUnResolved, objectTypeUnResolved, new GrantRevokeClause(null, roleName, false),
                new GrantRevokePrivilegeObjects());
    }

    public void setUserPrivilegeObject(UserIdentity userIdentity) {
        this.objects.setUserPrivilegeObjectList(Collections.singletonList(userIdentity));
    }
}
