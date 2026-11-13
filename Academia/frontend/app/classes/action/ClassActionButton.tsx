"use client"

import { useState } from "react";

interface ClassActionButtonProps {
    classId: string;
    initialStatus: ClassStatus;
}

export type ClassStatus = "AVAILABLE" | "INPROGRESS" | "FINISHED";

export default function ClassActionButton({ classId, initialStatus }: ClassActionButtonProps) {
    const [status, setStatus] = useState<ClassStatus>(initialStatus);
    const [message, setMessage] = useState("");
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState("");

    async function handleAction(action: "start" | "finish") {
        setLoading(true);
        setError("");
        setMessage("");

        try {
            const response = await fetch("/api/classes/action", {
                method: "PATCH",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ classId, action }),
            });

            if (!response.ok) {
                const data = await response.json();
                setError(data.error || "An error occurred");
                return;
            }
            const data = await response.json();
            setMessage(data.message);
            setStatus(action === "start" ? "INPROGRESS" : "FINISHED");
        } catch {
            setError("An error occurred. Please try again.");
        } finally {
            setLoading(false);
        }
    }

    return (
        <div>
            {status === "AVAILABLE" && (
                <>
                <p>Class is available to start.</p>
                <button type="button" onClick={() => handleAction("start")} disabled={loading}>
                    {loading ? "Starting..." : "Start Class"}
                </button>
                </>
            )}
            {status === "INPROGRESS" && (
                <>
                <p>Class is in progress.</p>
                <button type="button" onClick={() => handleAction("finish")} disabled={loading}>
                    {loading ? "Finishing..." : "Finish Class"}
                </button>
                </>
            )}
            {status === "FINISHED" && <p>Class has been finished.</p>}
            
            {message && <p>{message}</p>}
            {error && <p>{error}</p>}
        </div>
    )
}

