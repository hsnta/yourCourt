import { ApolloServer } from "@apollo/server";
import { ApolloGateway, IntrospectAndCompose } from "@apollo/gateway";
import { startStandaloneServer } from "@apollo/server/standalone";

const basePath = `${process.env.HTTPS_ENABLED == "true" ? "https" : "http"}://${
  process.env.BASE_PATH
}`;
const gateway = new ApolloGateway({
  supergraphSdl: new IntrospectAndCompose({
    subgraphs: [
      // {
      //   name: "drill-service",
      //   url: `${basePath}:${process.env.DRILL_SERVICE_PORT}/graphql`,
      // },
      {
        name: "user-service",
        url: `${basePath}:${process.env.USER_SERVICE_PORT}/graphql`,
      },
      {
        name: "user-performance-service",
        url: `${basePath}:${process.env.USER_PERFORMANCE_SERVICE_PORT}/graphql`,
      },
      {
        name: "workout-service",
        url: `${basePath}:${process.env.WORKOUT_SERVICE_PORT}/graphql`,
      },
    ],
  }),
});

const server = new ApolloServer({
  gateway,
  subscriptions: false,
  tracing: true,
  cors: false,
});
const { url } = await startStandaloneServer(server, {
  listen: { port: process.env.APOLLO_GATEWAY_SERVICE_PORT },
});
console.log(`🚀  Server ready at ${url}`);
