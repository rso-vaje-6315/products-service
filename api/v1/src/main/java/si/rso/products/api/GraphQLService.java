package si.rso.products.api;

import com.kumuluz.ee.graphql.GraphQLApplication;
import com.kumuluz.ee.graphql.annotations.GraphQLApplicationClass;
import si.rso.products.api.config.AuthRole;

import javax.annotation.security.DeclareRoles;

@GraphQLApplicationClass
@DeclareRoles({AuthRole.SERVICE, AuthRole.ADMIN, AuthRole.SELLER, AuthRole.CUSTOMER})
public class GraphQLService extends GraphQLApplication {
}
