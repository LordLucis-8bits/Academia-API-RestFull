import { getStudentInfo } from '@/lib/getStudentInfo';

export default async function StudentPlanPage() {
    const student = await getStudentInfo();

    return (
        <div>
            <h1 className="text-2xl font-bold">Student Plan</h1>
            <p className="text-gray-600">PlanType: {student.planType}</p>
            <p className="text-gray-600">PlanStart: {student.planStart}</p>
            <p className="text-gray-600">PlanEnd: {student.planEnd}</p>
            <p className="text-gray-600">PlanStatus: {student.planActive ? "ACTIVE" : "INACTIVE"}</p>
        </div>
    )
}