"use client"

import { useState } from "react";

interface EnrollButtonProps {
    classId: string;
    studentId: string;
}

export default function EnrollButton({ classId, studentId }: EnrollButtonProps) {
    const [success, setSuccess] = useState(false);
    const [error, setError] = useState("");
    const [message, setMessage] = useState("");
    const [loading, setLoading] = useState(false);

    async function handleEnroll() {
        setError("");
        setLoading(true);

        try {
            const response = await fetch("/api/enroll", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ classId, studentId }),
            });
            
            if (!response.ok) {
                const data = await response.json();
                setError(data.error || "An error occurred");
                return;
            }

            const data = await response.json();
            setMessage(data.message);
            setSuccess(true);
        } catch {
            setError("Unable to enroll. Please try again.");
        } finally {
            setLoading(false);
        }
    }

    return (
        <>
        <button type="button" onClick={handleEnroll} disabled={loading || success}>
            {loading ? "Enrolling..." : success ? "Enrolled!" : "Enroll"}
        </button>
        {message && <p>{message}</p>}
        {error && <p>{error}</p>}
        </>
    )
}
