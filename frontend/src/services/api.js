import axios from 'axios';

const API_BASE_URL = '/api';

const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

export const userService = {
  getBasicUsers: async () => {
    try {
      const response = await api.get('/users/basic');
      return response.data;
    } catch (error) {
      console.error('Error fetching users:', error);
      throw error;
    }
  },

  getUserReservations: async (userId) => {
    try {
      const response = await api.get(`/users/${userId}/reservations`);
      return response.data;
    } catch (error) {
      console.error(`Error fetching reservations for user ${userId}:`, error);
      throw error;
    }
  },

  deleteReservation: async (reservationId) => {
    try {
      const response = await api.delete(`/users/reservations/${reservationId}`);
      return response.status === 204;
    } catch (error) {
      console.error(`Error deleting reservation ${reservationId}:`, error);
      throw error;
    }
  },
};

export default api; 