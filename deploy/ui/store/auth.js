const defaultState = () => ({
  accessToken: null,
  userData: null
});

export const state = defaultState();

export const mutations = {
  SET_USER_DATA(state, payload) {
    state.userData = payload;
  },
  SET_ACCESS_TOKEN(state, payload) {
    state.accessToken = payload;
  },
  LOG_OUT(state, payload) {
    Object.assign(state, defaultState());
  }
};

export const actions = {};

export const getters = {
  isAuthenticated(state) {
    return !!state.accessToken;
  },
  isAdmin(state) {
    return state.userData.role === "ADMIN";
  },
  isUser(state) {
    return state.userData.role === "USER";
  }
};
