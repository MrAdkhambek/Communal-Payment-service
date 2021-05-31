export const state = () => ({});

export const actions = {
  fetchCategories() {
    return new Promise((resolve, reject) =>
      this.$axios
        .get(`/category/`)
        .then(response => resolve(response))
        .catch(err => reject(err))
    );
  },
  fetchCategory(ctx, id) {
    return new Promise((resolve, reject) =>
      this.$axios
        .get(`/category/${id}/`)
        .then(response => resolve(response))
        .catch(err => reject(error))
    );
  },
  createCategory(ctx, { name, photo }) {
    return new Promise((resolve, reject) =>
      this.$axios
        .post(`/category`, { name, photo }, {})
        .then(response => resolve(response))
        .catch(err => reject(err))
    );
  },
  deleteCategory(ctx, { id }) {
    return new Promise((resolve, reject) =>
      this.$axios
        .delete(`/category/${id}`)
        .then(response => resolve(response))
        .catch(err => reject(err))
    );
  },
  createCategoryService(ctx, { id, name, photo, min, max }) {
    return new Promise((resolve, reject) =>
      this.$axios
        .post(`/category/addCommunal/${id}`, { name, photo, min, max })
        .then(response => resolve(response))
        .catch(err => reject(error))
    );
  }
};
