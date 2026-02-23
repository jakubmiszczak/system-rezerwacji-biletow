import './DeleteConfirmModal.css';

const DeleteConfirmModal = ({ onConfirm, onCancel }) => {
  return (
    <div className="modal-overlay">
      <div className="modal-content">
        <h3 className="modal-title">Delete Reservation</h3>
        <p className="modal-message">
          Are you sure you want to delete this reservation?
        </p>
        <div className="modal-actions">
          <button 
            className="btn-cancel" 
            onClick={onCancel}
          >
            Cancel
          </button>
          <button 
            className="btn-delete" 
            onClick={onConfirm}
          >
            Delete
          </button>
        </div>
      </div>
    </div>
  );
};

export default DeleteConfirmModal; 