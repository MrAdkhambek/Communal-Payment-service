export const state = () => ({});

export const actions = {
  fetchServices() {
    return new Promise((resolve, reject) =>
      this.$axios
        .get(`/communal/`)
        .then(response => resolve(response))
        .catch(err => reject(err))
    );
  },
  fetchService(ctx, id) {
    return new Promise((resolve, reject) =>
      this.$axios
        .get(`/communal/${id}/`)
        .then(response => resolve(response))
        .catch(err => reject(error))
    );
  },
  deleteService(ctx, { id }) {
    return new Promise((resolve, reject) =>
      this.$axios
        .delete(`/communal/${id}/`)
        .then(response => resolve(response))
        .catch(err => reject(err))
    );
  }
};
