const subscribers = [];

export default function({ store, route, app: { $axios }, redirect }) {
  $axios.onRequest(config => {
    // check if the user is authenticated
    if (store.state.auth.accessToken) {
      // set the Authorization header using the access token
      config.headers.Authorization = "Bearer " + store.state.auth.accessToken;
    }

    return config;
  });

  $axios.onResponseError(error => {
    // check if the user is authenticated

    if (error.response.status === 401) {
      redirect("/login");
    }
    //   const retryOriginalRequest = new Promise(resolve => {
    //     const { config, response } = error;
    //     const originalRequest = config;

    //     this.addSubscriber(accessToken => {
    //       // Make sure to assign accessToken according to your response.
    //       // Check: https://pixinvent.ticksy.com/ticket/2413870
    //       // Change Authorization header
    //       originalRequest.headers.Authorization = `${this.jwtConfig.tokenType} ${accessToken}`;
    //       resolve(this.axiosIns(originalRequest));
    //     });
    //   });
    //   return retryOriginalRequest;

    //   return config;
  });
}
