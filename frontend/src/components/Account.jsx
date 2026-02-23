import { useState, useEffect } from 'react';
import { userService } from '../services/api';
import DeleteConfirmModal from './DeleteConfirmModal';
import './Account.css';

const Account = ({ user, isExpanded, onToggle }) => {
  const [reservations, setReservations] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [showDeleteModal, setShowDeleteModal] = useState(false);
  const [reservationToDelete, setReservationToDelete] = useState(null);
  const [successMessage, setSuccessMessage] = useState('');

  useEffect(() => {
    if (isExpanded && reservations.length === 0) {
      fetchReservations();
    }
  }, [isExpanded]);

  const fetchReservations = async () => {
    setLoading(true);
    setError(null);
    try {
      const data = await userService.getUserReservations(user.id);
      setReservations(data);
    } catch (err) {
      setError('Failed to load reservations');
    } finally {
      setLoading(false);
    }
  };

  const handleDeleteReservation = async (reservationId) => {
    try {
      await userService.deleteReservation(reservationId);
      setReservations(prev => prev.filter(r => r.id !== reservationId));
      setShowDeleteModal(false);
      setReservationToDelete(null);

      setSuccessMessage('Reservation deleted!');
      setTimeout(() => setSuccessMessage(''), 3000);

    } catch (err) {
      console.error('Failed to delete reservation:', err);
      alert('Failed to delete reservation');
    }
  };

  const openDeleteModal = (reservation) => {
    setReservationToDelete(reservation);
    setShowDeleteModal(true);
  };

  const formatDate = (dateString) => {
    const date = new Date(dateString);
    return date.toLocaleDateString('en-GB', {
      day: '2-digit',
      month: '2-digit',
      year: 'numeric'
    });
  };

  const formatPrice = (price) => {
    return parseFloat(price).toFixed(2);
  };

  return (
    <>
      <div className="account-item">
        {successMessage && (
          <div className="success-message">
            {successMessage}
          </div>
        )}

        <div className="account-header" onClick={onToggle}>
          <div className="account-info">
            <h3 className="account-name">{user.login}</h3>
            <p className="account-email">{user.email}</p>
          </div>
          <div className={`expand-arrow ${isExpanded ? 'expanded' : ''}`}>
            <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2">
              <polyline points="6,9 12,15 18,9" />
            </svg>
          </div>
        </div>

        {isExpanded && (
          <div className="account-content">
            <h4 className="reservations-title">Reservations</h4>
            
            {loading ? (
              <div className="loading">Loading reservations...</div>
            ) : error ? (
              <div className="error">{error}</div>
            ) : reservations.length === 0 ? (
              <div className="no-reservations">
                No reservations found for this account.
              </div>
            ) : (
              <div className="reservations-list">
                {reservations.map(reservation => (
                  <div key={reservation.id} className="reservation-item">
                    <div className="reservation-header">
                      <span className="reservation-info">
                        Reservation date: {formatDate(reservation.reservationDateTime)}, 
                        Movie: {reservation.movieTitle},
                        Cinema: {reservation.cinemaName},
                        Hall number: {reservation.hallNumber}, 
                        Total price: {formatPrice(reservation.totalPrice)}zł
                      </span>
                      <button 
                        className="delete-btn"
                        onClick={() => openDeleteModal(reservation)}
                      >
                        <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2">
                          <polyline points="3,6 5,6 21,6" />
                          <path d="m19,6v14a2,2 0 0,1-2,2H7a2,2 0 0,1-2-2V6m3,0V4a2,2 0 0,1,2-2h4a2,2 0 0,1,2,2v2" />
                          <line x1="10" y1="11" x2="10" y2="17" />
                          <line x1="14" y1="11" x2="14" y2="17" />
                        </svg>
                      </button>
                    </div>
                    
                    {reservation.ticketData && reservation.ticketData.length > 0 && (
                      <div className="tickets-section">
                        <h5 className="tickets-title">Tickets</h5>
                        <div className="tickets-list">
                          {reservation.ticketData.map((ticket, index) => (
                            <div key={index} className="ticket-item">
                              Row: {ticket.row}, Column: {ticket.number}
                            </div>
                          ))}
                        </div>
                      </div>
                    )}
                  </div>
                ))}
              </div>
            )}
          </div>
        )}
      </div>

      {showDeleteModal && reservationToDelete && (
        <DeleteConfirmModal
          onConfirm={() => handleDeleteReservation(reservationToDelete.id)}
          onCancel={() => {
            setShowDeleteModal(false);
            setReservationToDelete(null);
          }}
        />
      )}
    </>
  );
};

export default Account; 