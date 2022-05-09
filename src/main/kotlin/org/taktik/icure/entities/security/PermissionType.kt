package org.taktik.icure.entities.security

enum class PermissionType {
	AUTHENTICATE,
	ADMIN,
	PATIENT_VIEW,
	PATIENT_CREATE,
	PATIENT_CHANGE_DELETE,
	MEDICAL_DATA_VIEW,
	MEDICAL_DATA_CREATE,
	MEDICAL_CHANGE_DELETE,
	FINANCIAL_DATA_VIEW,
	FINANCIAL_DATA_CREATE,
	FINANCIAL_CHANGE_DELETE
}
